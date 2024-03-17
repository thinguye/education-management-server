package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Result;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.IEnrollmentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ILecturerRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IResultRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IStudentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;
import com.capstone.educationmanagementserver.requests.enrollment.AddEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UpdateEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UploadGradeRequest;
import com.capstone.educationmanagementserver.services.interfaces.IEnrollmentService;
import com.capstone.educationmanagementserver.services.interfaces.IStudentService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInQuarterService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService implements IEnrollmentService {
	Double[] grade = { 0.0, 40.0, 45.0, 50.0, 55.0, 60.0, 65.0, 70.0, 75.0, 80.0, 85.0, 90.0 };
	Double[] gpa = { 0.0, 0.7, 1.0, 1.3, 1.7, 2.0, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0 };
	String[] gradeLetter = { "F", "D-", "D", "D+", "C-", "C", "C+", "B-", "B", "B+", "A-", "A" };
	@Autowired
	private IEnrollmentRepository iEnrollmentRepository;
	@Autowired
	private ISubjectInQuarterRepository iSubjectInQuarterRepository;
	@Autowired
	private IStudentService iStudentService;
	@Autowired
	private ISubjectInQuarterService iSubjectInQuarterService;
	@Autowired
	private ISubjectInBlockRepository iSubjectInBlockRepository;
	@Autowired
	private IResultRepository iResultRepository;
	@Autowired
	private ILecturerRepository iLecturerRepository;
	@Autowired
	private IStudentRepository iStudentRepository;

	@Override
	public List<Enrollment> getAllEnrollment() {
		return iEnrollmentRepository.findAll();
	}

	@Override
	public Enrollment getEnrollmentById(String id) {
		return iEnrollmentRepository.findById(id);
	}

	@Override
	public void removeEnrollment(String id) {
		Enrollment e = iEnrollmentRepository.findById(id);
		SubjectInQuarter s = e.getSubject();
		s.setNumberOfStudents(s.getNumberOfStudents() - 1);
		iSubjectInQuarterRepository.save(s);
		iEnrollmentRepository.remove(e);
	}

	@Override
	public void addEnrollment(AddEnrollmentRequest request) {
		SubjectInQuarter subject = iSubjectInQuarterService.getSubjectById(request.getSubject());
		if (subject.getNumberOfStudents() < subject.getMaxStudents()) {
			Student st = iStudentService.getStudentByCode(request.getStudent());
			List<Subject> learnt = st.getSubjects();
			List<Subject> prev = subject.getSubject().getPreviousCourses();
			List<Subject> prere = subject.getSubject().getPrerequisites();
			if ((prere.isEmpty() || learnt.containsAll(prere)) && (prev.isEmpty() || learnt.containsAll(prev))) {
				Enrollment temp = iEnrollmentRepository.findByStudentSubject(subject, st);
				if (temp == null) {
					Enrollment e = Enrollment.builder().student(st).subject(subject).build();
					iEnrollmentRepository.save(e);
					Result res = iResultRepository.getResultByStudentAndQuarter(st, subject.getQuarter());
					if (res != null) {
						List<Enrollment> enrollments = res.getEnrollments();
						enrollments.add(e);
						res.setEnrollments(enrollments);
					} else {
						List<Enrollment> enrollments = new ArrayList<>();
						enrollments.add(e);
						res = Result.builder().quarter(subject.getQuarter()).student(st).enrollments(enrollments)
								.build();
					}
					iResultRepository.save(res);
					subject.setNumberOfStudents(subject.getNumberOfStudents() + 1);
					iSubjectInQuarterRepository.save(subject);
				}
			}

		}
	}

	@Override
	public void updateEnrollment(UpdateEnrollmentRequest request) {
		Enrollment e = iEnrollmentRepository.findById(request.getId());
		Student st = e.getStudent();
		Result result = iResultRepository.getResultByEnrollment(e);
		Subject s = e.getSubject().getSubject();
		Curriculum c = e.getStudent().getCurriculum();
		Double score = request.getGrade();
		for (int i = 0; i < grade.length; i++) {
			if (score < grade[i]) {
				e.setGrade(gpa[i - 1]);
				e.setGradeLetter(gradeLetter[i - 1]);
				if (iSubjectInBlockRepository.isExistInCurriculum(c, s)) {
					result.setGpa(
							(score * (s.getLabCredit() + s.getTheoryCredit()) + result.getGpa() * result.getCredit())
									/ (s.getLabCredit() + s.getTheoryCredit() + result.getCredit()));
					result.setCredit(result.getCredit() + s.getLabCredit() + s.getTheoryCredit());
					st.setGpa((score * (s.getLabCredit() + s.getTheoryCredit()) + st.getGpa() * st.getCredit())
							/ (s.getLabCredit() + s.getTheoryCredit() + st.getCredit()));
					st.setCredit(st.getCredit() + s.getLabCredit() + s.getTheoryCredit());
				}
				break;
			}
			if (i == grade.length - 1 && score >= grade[i]) {
				e.setGrade(gpa[i]);
				e.setGradeLetter(gradeLetter[i]);
				if (iSubjectInBlockRepository.isExistInCurriculum(c, s)) {
					result.setGpa(
							(score * (s.getLabCredit() + s.getTheoryCredit()) + result.getGpa() * result.getCredit())
									/ (s.getLabCredit() + s.getTheoryCredit() + result.getCredit()));
					result.setCredit(result.getCredit() + s.getLabCredit() + s.getTheoryCredit());
					st.setGpa((score * (s.getLabCredit() + s.getTheoryCredit()) + st.getGpa() * st.getCredit())
							/ (s.getLabCredit() + s.getTheoryCredit() + st.getCredit()));
					st.setCredit(st.getCredit() + s.getLabCredit() + s.getTheoryCredit());
				}
			}
		}
		iEnrollmentRepository.save(e);
		iResultRepository.save(result);
		iStudentRepository.save(st);
	}

	@Override
	public void addEnrollmentFromFile(UploadEnrollementRequest request) {
		try {
			InputStream inputStream = request.getFile().getInputStream();
			SubjectInQuarter subject = iSubjectInQuarterService.getSubjectById(request.getId());
			Integer noStudent = subject.getNumberOfStudents();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 2; i <= worksheet.getPhysicalNumberOfRows(); i++) {
					if (noStudent < subject.getMaxStudents()) {
						XSSFRow row = worksheet.getRow(i);
						String code = String.valueOf(row.getCell(1).getRawValue());
						Student s = iStudentService.getStudentByCode(code);
						List<Subject> learnt = s.getSubjects();
						List<Subject> prev = subject.getSubject().getPreviousCourses();
						List<Subject> prere = subject.getSubject().getPrerequisites();
						if ((prere.isEmpty() || learnt.containsAll(prere))
								&& (prev.isEmpty() || learnt.containsAll(prev))) {
							Enrollment temp = iEnrollmentRepository.findByStudentSubject(subject, s);
							if (temp == null) {
								Enrollment tempEnrollment = Enrollment.builder().student(s).subject(subject).build();
								iEnrollmentRepository.save(tempEnrollment);
								Result res = iResultRepository.getResultByStudentAndQuarter(s, subject.getQuarter());
								if (res != null) {
									List<Enrollment> enrollments = res.getEnrollments();
									enrollments.add(tempEnrollment);
									res.setEnrollments(enrollments);
								} else {
									List<Enrollment> enrollments = new ArrayList<>();
									enrollments.add(tempEnrollment);
									res = Result.builder().quarter(subject.getQuarter()).student(s)
											.enrollments(enrollments).build();
								}
								iResultRepository.save(res);
								noStudent++;
							}
						}
					}
				}
				subject.setNumberOfStudents(noStudent);
				iSubjectInQuarterRepository.save(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Enrollment> getStudentBySubject(String id) {
		SubjectInQuarter s = iSubjectInQuarterService.getSubjectById(id);
		return iEnrollmentRepository.findBySubject(s);
	}

	@Override
	public Enrollment getEnrollmentByStudentSubject(SubjectInQuarter s, Student st) {
		return iEnrollmentRepository.findByStudentSubject(s, st);
	}

	@Override
	public List<Enrollment> getSubjectByStudent(String id) {
		Student student = iStudentService.getStudentById(id);
		return iEnrollmentRepository.findSubjectByStudent(student);
	}

	@Override
	public List<Enrollment> getEnrrollmentsByLecturer(String email) {
		Lecturer lecturer = iLecturerRepository.findByEmail(email);
		return iEnrollmentRepository.findByLecturer(lecturer);
	}

	@Override
	public void updateGradeFromFile(UploadGradeRequest request) {
		try {
			InputStream inputStream = request.getFile().getInputStream();
			SubjectInQuarter subject = iSubjectInQuarterRepository.findById(request.getSubject());
			Quarter quarter = subject.getQuarter();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 5; i <= worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(1).getRawValue());
					Student s = iStudentService.getStudentByCode(code);
					Result result = iResultRepository.getResultByStudentAndQuarter(s, quarter);
					Enrollment temp = iEnrollmentRepository.findByStudentSubject(subject, s);
					Double score = Double.valueOf(row.getCell(3).getNumericCellValue());
					String gradeInLetter = row.getCell(4).getStringCellValue();
					temp.setGradeLetter(gradeInLetter);
					if (!gradeInLetter.equalsIgnoreCase("F")) {
						if (score >= grade[grade.length - 1]) {
							temp.setGrade(gpa[gpa.length - 1]);
							temp.setGradeLetter(gradeLetter[gradeLetter.length - 1]);
						} else if (score < grade[1]) {
							temp.setGradeLetter(gradeLetter[0]);
						} else {
							for (int j = 2; j < grade.length; j++) {
								if (score < grade[j]) {
									temp.setGrade(gpa[j - 1]);
									temp.setGradeLetter(gradeLetter[j - 1]);
									break;
								}
							}
						}
						List<Subject> learnt = s.getSubjects();
						if (!learnt.contains(temp.getSubject().getSubject())) {
							result.setGpa((temp.getGrade()
									* (subject.getSubject().getLabCredit() + subject.getSubject().getTheoryCredit())
									+ result.getGpa() * result.getCredit())
									/ (subject.getSubject().getLabCredit() + subject.getSubject().getTheoryCredit()
											+ result.getCredit()));
							result.setCredit(result.getCredit() + subject.getSubject().getLabCredit()
									+ subject.getSubject().getTheoryCredit());
							s.setGpa((temp.getGrade()
									* (subject.getSubject().getLabCredit() + subject.getSubject().getTheoryCredit())
									+ s.getGpa() * s.getCredit())
									/ (subject.getSubject().getLabCredit() + subject.getSubject().getTheoryCredit()
											+ s.getCredit()));
							s.setCredit(s.getCredit() + subject.getSubject().getLabCredit()
									+ subject.getSubject().getTheoryCredit());
							Block block = iSubjectInBlockRepository.findBySubject(subject.getSubject(),
									s.getCurriculum());
							List<Subject> subjectsInBlock = iSubjectInBlockRepository.getAllSubjectsInBlock(block)
									.stream().map(SubjectInBlock::getSubject).toList();
							if (!learnt.containsAll(subjectsInBlock)) {
								Integer credits = 0;
								for (Subject sub : subjectsInBlock) {
									if (learnt.contains(sub)) {
										credits += sub.getLabCredit() + sub.getTheoryCredit();
									}
									if (credits >= block.getCredit()) {
										break;
									}
								}
								if (credits < block.getCredit()) {
									credits += subject.getSubject().getLabCredit()
											+ subject.getSubject().getTheoryCredit();
									if (credits <= block.getCredit()) {
										s.setActualCredit(s.getActualCredit() + subject.getSubject().getLabCredit()
												+ subject.getSubject().getTheoryCredit());
									} else {
										s.setActualCredit(s.getActualCredit() + block.getCredit() - credits);
									}
								}

							}
							learnt.add(subject.getSubject());
							s.setSubjects(learnt);
						}

					}else {
						temp.setGradeLetter("F");
					}
					iEnrollmentRepository.save(temp);
					iResultRepository.save(result);
					iStudentRepository.save(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
