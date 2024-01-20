package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.List;

import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.IEnrollmentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;
import com.capstone.educationmanagementserver.requests.enrollment.AddEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UpdateEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;
import com.capstone.educationmanagementserver.services.interfaces.IEnrollmentService;
import com.capstone.educationmanagementserver.services.interfaces.IStudentService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInQuarterService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EnrollmentService implements IEnrollmentService {
	Double[] grade = { 0.0, 50.0, 55.0, 60.0, 65.0, 70.0, 75.0, 80.0, 85.0, 90.0 };
	Double[] gpa = { 0.0, 1.3, 1.7, 2.0, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0 };
	String[] gradeLetter = { "F", "D", "D+", "C", "C+", "B-", "B", "B+", "A-", "A" };
	@Autowired
	private IEnrollmentRepository iEnrollmentRepository;
	@Autowired
	private ISubjectInQuarterRepository iSubjectInQuarterRepository;
	@Autowired
	private IStudentService iStudentService;
	@Autowired
	private ISubjectInQuarterService iSubjectInQuarterService;

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
		Student st = iStudentService.getStudentByCode(request.getStudent());
		SubjectInQuarter subject = iSubjectInQuarterService.getSubjectById(request.getSubject());
		Enrollment e = Enrollment.builder().student(st).subject(subject).build();
		iEnrollmentRepository.save(e);
		subject.setNumberOfStudents(subject.getNumberOfStudents() + 1);
		iSubjectInQuarterRepository.save(subject);
	}

	@Override
	public void updateEnrollment(UpdateEnrollmentRequest request) {
		Enrollment e = iEnrollmentRepository.findById(request.getId());
		Double score = request.getGrade();
		for (int i = 0; i < grade.length; i++) {
			if (score < grade[i]) {
				e.setGrade(gpa[i - 1]);
				e.setGradeLetter(gradeLetter[i - 1]);
				break;
			}
			if (i == grade.length - 1 && score >= grade[i]) {
				e.setGrade(gpa[i]);
				e.setGradeLetter(gradeLetter[i]);
			}
		}
		iEnrollmentRepository.save(e);
	}

	@Override
	public void addEnrollmentFromFile(UploadEnrollementRequest request) {
		try {
			InputStream inputStream = request.getFile().getInputStream();
			SubjectInQuarter subject = iSubjectInQuarterService.getSubjectById(request.getId());
			Integer noStudent = 0;
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 2; i <= worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(1).getRawValue());
					Student s = iStudentService.getStudentByCode(code);
					Enrollment temp = iEnrollmentRepository.findByStudentSubject(subject, s);
					if (temp == null) {
						Enrollment tempEnrollment = Enrollment.builder().student(s).subject(subject).build();
						iEnrollmentRepository.save(tempEnrollment);
						noStudent += 1;
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

}
