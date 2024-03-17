package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.models.TimeTable;
import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IEnrollmentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IGenerationRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ILecturerRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IYearRepository;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;
import com.capstone.educationmanagementserver.requests.quarter.AddSubjectToQuarter;
import com.capstone.educationmanagementserver.requests.response.NumberStudentResponse;
import com.capstone.educationmanagementserver.services.interfaces.IQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectService;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SubjectInQuarterService implements ISubjectInQuarterService {

	@Autowired
	private ISubjectInQuarterRepository iSubjectInQuarterRepository;
	@Autowired
	private ISubjectService iSubjectService;
	@Autowired
	private IQuarterService iQuarterService;
	@Autowired
	private IYearRepository iYearRepository;
	@Autowired
	private ILecturerRepository iLecturerRepository;
	@Autowired
	private IEnrollmentRepository iEnrollmentRepository;
	@Autowired
	private IGenerationRepository iGenerationRepository;
	@Autowired
	private ISubjectRepository iSubjectRepository;

	Map<String, String> getHashMap() {
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("Mon", "Thứ Hai");
		hashMap.put("Tue", "Thứ Ba");
		hashMap.put("Wed", "Thứ Tư");
		hashMap.put("Thu", "Thứ Năm");
		hashMap.put("Fri", "Thứ Sáu");
		hashMap.put("Sat", "Thứ Bảy");
		return hashMap;
	}

	@Override
	public void addSubject(AddSubjectToQuarter request) {
		SubjectInQuarter subjectInQuarter = SubjectInQuarter.builder().subject(request.getSubject())
				.quarter(request.getQuarter()).lecturer(request.getLecturer()).maxStudents(request.getMaxStudents())
				.build();
		iSubjectInQuarterRepository.save(subjectInQuarter);
	}

	@Override
	public List<SubjectInQuarter> getAll() {
		return iSubjectInQuarterRepository.findAll();
	}

	@Override
	public List<SubjectInQuarter> getByLecturer(String id) {
		Lecturer lecturer = iLecturerRepository.findById(id);
		return iSubjectInQuarterRepository.findByLecturer(lecturer);
	}

	@Override
	public SubjectInQuarter getBySubjectQuarter(String subject, String quarter, String year) {
		Subject s = iSubjectService.getSubjectByCode(subject);
		Quarter q = iQuarterService.getQuarterByQuarterYear(quarter, year);
		return iSubjectInQuarterRepository.findBySubjectQuarter(s, q);
	}

	@Override
	public List<SubjectInQuarter> getTeachingSubjects(String id) {
		Lecturer lec = iLecturerRepository.findById(id);
		return iSubjectInQuarterRepository.findByLecturerLastQuarter(lec);
	}

	@Override
	public void removeSubject(String id) {
		SubjectInQuarter s = iSubjectInQuarterRepository.findById(id);
		iSubjectInQuarterRepository.remove(s);

	}

	@Override
	public SubjectInQuarter getSubjectById(String id) {
		return iSubjectInQuarterRepository.findById(id);
	}

	@Override
	public void addSubjectsFromFile(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			Map<String, String> hashMap = getHashMap();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				int i = 0;
				String year = "";
				Quarter quarter = null;
				for (i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(0).getStringCellValue());
					if (code.trim().equals("Năm")) {
						year = row.getCell(1).getStringCellValue();
					}
					if (code.trim().contains("Học kì")) {
						quarter = iQuarterService.getQuarterByQuarterYear(code, year);
					}
					if (code.trim().equals("Mã môn")) {
						i++;
						XSSFRow subRow = worksheet.getRow(i);
						do {
							Subject s = iSubjectService.getSubjectByCode(subRow.getCell(0).getStringCellValue());
							SubjectInQuarter subjectInQuarter = iSubjectInQuarterRepository.findBySubjectQuarter(s,
									quarter);
							if (subjectInQuarter == null) {
								Lecturer lecturerCode = iLecturerRepository
										.findByCode(subRow.getCell(2).getStringCellValue());
								Integer maxSize = (int) subRow.getCell(3).getNumericCellValue();
								List<TimeTable> timeTable = new ArrayList<>();
								String timeS = subRow.getCell(4).getStringCellValue();
								if (timeS.length() > 0) {
									String[] time = timeS.split(";");
									if (time.length > 0) {
										for (String t : time) {
											String[] specificTime = t.split(",");
											Date from = DateUtils.parseDate(specificTime[3],
													new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
											Date to = DateUtils.parseDate(specificTime[4],
													new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
											timeTable.add(new TimeTable(specificTime[1], hashMap.get(specificTime[0]),
													Integer.parseInt(specificTime[2]), from, to));
										}
									}
								}
								SubjectInQuarter subject = SubjectInQuarter.builder().subject(s).lecturer(lecturerCode)
										.maxStudents(maxSize).quarter(quarter).timeTable(timeTable).build();
								iSubjectInQuarterRepository.save(subject);
							} else {
								List<TimeTable> timeTable = new ArrayList<>();
								String timeS = subRow.getCell(4).getStringCellValue();
								if (timeS.length() > 0) {
									String[] time = timeS.split(";");
									if (time.length > 0) {
										for (String t : time) {
											String[] specificTime = t.split(",");
											Date from = DateUtils.parseDate(specificTime[3],
													new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
											Date to = DateUtils.parseDate(specificTime[4],
													new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
											timeTable.add(new TimeTable(specificTime[1], hashMap.get(specificTime[0]),
													Integer.parseInt(specificTime[2]), from, to));
										}
									}
								}
								subjectInQuarter.setTimeTable(timeTable);
								iSubjectInQuarterRepository.save(subjectInQuarter);
							}
							subRow = worksheet.getRow(++i);
						} while (!subRow.getCell(0).getStringCellValue().equals("Năm")
								&& !subRow.getCell(0).getStringCellValue().contains("Học kì")
								&& !subRow.getCell(0).getStringCellValue().trim().equals(""));
						i--;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public NumberStudentResponse getNoStudents(String id, String subject, String generation) {
		Integer sum = 0;
		Double gpaAverage = 0.00;
		Integer sumFail = 0;
		Integer sumPass = 0;
		Integer sumNotGrade = 0;
		List<String> codes = new ArrayList<>();
		List<Integer> noStudents = new ArrayList<>();
		List<Integer> passStudents = new ArrayList<>();
		List<Integer> failStudents = new ArrayList<>();
		List<Integer> notGradeStudents = new ArrayList<>();
		List<String> subjectName = new ArrayList<>();
		List<Double> gpa = new ArrayList<>();
		Map<String, Boolean> map = new HashMap<>();
		Subject s = iSubjectRepository.findById(subject);
		Generation gen = null;

		List<SubjectInQuarter> subjects = new ArrayList<>();
		if (s == null) {
			subjects = getByLecturer(id);
		} else {
			Lecturer l = iLecturerRepository.findById(id);
			subjects = iSubjectInQuarterRepository.findBySubjectLecturer(l, s);
		}
		for (SubjectInQuarter sub : subjects) {
			String code = sub.getSubject().getCode();
			sum += sub.getNumberOfStudents();
			List<Enrollment> failEnrollments = iEnrollmentRepository.findBySubjectFail(sub, gen);
			List<Enrollment> passEnrollments = iEnrollmentRepository.findBySubjectPass(sub, gen);
			Integer haveNotGrade = sub.getNumberOfStudents() - (failEnrollments.size() + passEnrollments.size());
			sumFail += failEnrollments.size();
			sumPass += passEnrollments.size();
			sumNotGrade += haveNotGrade;
			double sumGpa = passEnrollments.isEmpty() ? 0
					: passEnrollments.stream().mapToDouble(Enrollment::getGrade).sum();
			gpaAverage += sumGpa;
			if (map.get(code) == null) {
				map.put(code, true);
				codes.add(code);
				noStudents.add(sub.getNumberOfStudents());
				passStudents.add(passEnrollments.size());
				failStudents.add(failEnrollments.size());
				notGradeStudents.add(haveNotGrade);
				gpa.add(passEnrollments.isEmpty() ? 0 : sumGpa / passEnrollments.size());
				subjectName.add(sub.getSubject().getName());
			} else {
				int index = codes.indexOf(code);
				gpa.add(index,
						passStudents.get(index) + passEnrollments.size() == 0 ? 0
								: (gpa.get(index) * passStudents.get(index) + sumGpa)
										/ (passStudents.get(index) + passEnrollments.size()));
				noStudents.add(index, noStudents.get(index) + sub.getNumberOfStudents());
				passStudents.add(index, passStudents.get(index) + passEnrollments.size());
				failStudents.add(index, failStudents.get(index) + failEnrollments.size());
				notGradeStudents.add(index, notGradeStudents.get(index) + haveNotGrade);
			}

		}
		List<Integer> dic = new ArrayList<>();
		dic.add(sumPass);
		dic.add(sumFail);
		dic.add(sumNotGrade);
		return new NumberStudentResponse(sum, sumPass == 0 ? 0 : gpaAverage / sumPass, dic, noStudents, passStudents,
				failStudents, notGradeStudents, subjectName, gpa);
	}

	@Override
	public List<Subject> getDistinctSubjects(String id) {
		List<SubjectInQuarter> subjects = getByLecturer(id);
		List<Subject> sub = subjects.stream().map(SubjectInQuarter::getSubject).toList();
		return sub.stream().distinct().toList();
	}

}
