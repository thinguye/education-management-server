package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.capstone.educationmanagementserver.controllers.AuthController;
import com.capstone.educationmanagementserver.enums.ERole;
import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.enums.Status;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Requirement;
import com.capstone.educationmanagementserver.models.Result;
import com.capstone.educationmanagementserver.models.Role;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.models.TimeTable;
import com.capstone.educationmanagementserver.models.Year;
import com.capstone.educationmanagementserver.repositories.interfaces.IBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IEnrollmentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IGenerationRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IMajorRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IQuarterRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IRequirementRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IResultRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IStudentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IYearRepository;
import com.capstone.educationmanagementserver.requests.auth.SignupRequest;
import com.capstone.educationmanagementserver.requests.response.Undergraduate;
import com.capstone.educationmanagementserver.requests.student.AddStudentRequest;
import com.capstone.educationmanagementserver.requests.student.CreditResponse;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;
import com.capstone.educationmanagementserver.services.interfaces.IStudentService;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

@Service
public class StudentService implements IStudentService {
	@Autowired
	IStudentRepository iStudentRepository;
	@Autowired
	ICurriculumService iCurriculumService;
	@Autowired
	AuthController authController;
	@Autowired
	IRequirementRepository iRequirementRepository;
	@Autowired
	IMajorRepository iMajorRepository;
	@Autowired
	IEnrollmentRepository iEnrollmentRepository;
	@Autowired
	IBlockRepository iBlockRepository;
	@Autowired
	ISubjectInBlockRepository iSubjectInBlockRepository;
	@Autowired
	IGenerationRepository iGenerationRepository;
	@Autowired
	ICurriculumRepository iCurriculumRepository;
	@Autowired
	IResultRepository iResultRepository;
	@Autowired
	IQuarterRepository iQuarterRepository;
	@Autowired
	IYearRepository iYearRepository;
	@Autowired
	ISubjectInQuarterRepository iSubjectInQuarterRepository;

	@Override
	public void addStudent(AddStudentRequest student) {
		if (getStudentByCode(student.getCode()) == null) {
			Set<String> roles = new HashSet<>();
			roles.add("Student");
			String password = student.getEmail().substring(0,
					student.getEmail().indexOf(".", student.getEmail().indexOf(".") + 1)) + "@123";
			authController.registerUser(new SignupRequest(student.getFirstName(), student.getLastName(),
					student.getEmail(), roles, password));
			Curriculum c = iCurriculumService.getCirriculumByOrganization(student.getDepartment(),
					student.getGeneration());
			int gen = Integer.parseInt(student.getGeneration().getCode());
			boolean isExist = true;
			while (c == null) {
				gen--;
				Generation g = iGenerationRepository.findByCode(String.valueOf(gen));
				c = iCurriculumService.getCirriculumByOrganization(student.getDepartment(), g);
				isExist = false;
			}
			if (!isExist) {
				List<Generation> gens = c.getGeneration();
				gens.add(student.getGeneration());
				c.setGeneration(gens);
				iCurriculumRepository.save(c);
			}
			Student st = Student.builder().code(student.getCode()).gender(Gender.valueOf(student.getGender()))
					.firstName(student.getFirstName()).lastName(student.getLastName())
					.middleName(student.getMiddleName()).email(student.getEmail()).dateOfBirth(student.getDateOfBirth())
					.generation(student.getGeneration()).curriculum(c).build();
			iStudentRepository.save(st);
		}
	}

	@Override
	public void updateStudentProfile(UpdateStudentProfileRequest student) {
		try {
			Student st = iStudentRepository.findById(student.getId());
			st.updateStudent(student);
			Curriculum c = iCurriculumService.getCirriculumByOrganization(student.getDepartment(),
					student.getGeneration());
			st.setCurriculum(c);
			iStudentRepository.save(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeStudent(String id) {
		try {
			Student st = iStudentRepository.findById(id);
			iStudentRepository.remove(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> getAllStudent() {
		return iStudentRepository.findAll();
	}

	@Override
	public Student getStudentByCode(String code) {
		return iStudentRepository.findByCode(code);
	}

	@Override
	public Student getStudentById(String id) {
		return iStudentRepository.findById(id);
	}

	@Override
	public Student getStudentByEmail(String email) {
		return iStudentRepository.findByEmail(email);
	}

	@Override
	public void addStudentFromFile(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 2; i <= worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(1).getRawValue());
					Student s = getStudentByCode(code);
					if (s == null) {
						String lastName = row.getCell(2).getStringCellValue();
						String middleName = row.getCell(3).getStringCellValue();
						String firstName = row.getCell(4).getStringCellValue();
						String genderStr = row.getCell(5).getStringCellValue();
						Gender gender = Gender.OTHER;
						if (genderStr.equalsIgnoreCase("Male")) {
							gender = Gender.MALE;
						} else if (genderStr.equalsIgnoreCase("Female")) {
							gender = Gender.FEMALE;
						}
						String email = row.getCell(6).getStringCellValue();
						Major department = iMajorRepository.findByCode(row.getCell(7).getStringCellValue());
						Generation generation = iGenerationRepository.findByCode(row.getCell(8).getStringCellValue());
						String dateS = row.getCell(9).getStringCellValue();
						Date date = DateUtils.parseDate(dateS, new String[] { "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy" });
						AddStudentRequest request = new AddStudentRequest(code, firstName, middleName, lastName,
								genderStr, email, department, generation, date);
						addStudent(request);
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> getStudentGraduated() {
		return iStudentRepository.findAll().stream().filter(s -> {
			try {
				if (s.getCredit() >= s.getCurriculum().getCredit()) {
					if (s.getConditions().containsAll(s.getCurriculum().getConditions())) {
						return true;
					}
				}
			} catch (IOException e) {
				// handle exception
			}
			return false;
		}).collect(Collectors.toList());
	}

	@Override
	public Undergraduate getStudentUndergraduated() {
		List<Student> allStudents = iStudentRepository.findAll();
		Requirement ieltsRequirement = iRequirementRepository.findByCode("IELTS 6.0");
		Requirement qpRequirement = iRequirementRepository.findByCode("MET 100");
		Integer qp = 0;
		Integer ielts = 0;
		Integer credit = 0;
		Integer graduated = 0;
		Integer leaved = 0;
		Integer undergraduate = 0;
		Integer waiting = 0;
		Integer qpButDone = 0;
		Integer ieltsButDone = 0;
		for (Student s : allStudents) {
			if (s.getStatus().equals(Status.LEAVED)) {
				leaved++;
			} else {
				if (!s.getConditions().contains(ieltsRequirement)) {
					ielts++;
					if (s.getActualCredit() >= s.getCurriculum().getCredit()) {
						ieltsButDone++;
					}
				}
				if (!s.getConditions().contains(qpRequirement)) {
					qp++;
					if (s.getActualCredit() >= s.getCurriculum().getCredit()) {
						qpButDone++;
					}
				}
				if (s.getActualCredit() < s.getCurriculum().getCredit()) {
					credit++;
				}
				if (s.getStatus().equals(Status.GRADUATED)) {
					graduated++;
				} else if (s.getStatus().equals(Status.INCOMPLETE)) {
					undergraduate++;
				} else {
					waiting++;
				}
			}
		}
		Undergraduate u = new Undergraduate(ielts, qp, credit, graduated, leaved, undergraduate, waiting, qpButDone,
				ieltsButDone);
		return u;
	}

	@Override
	public List<Student> getListStudentUnderGraduate() {
		return iStudentRepository.findAll().stream().filter(s -> {
			try {
				if (s.getActualCredit() < s.getCurriculum().getCredit()
						|| !s.getConditions().containsAll(s.getCurriculum().getConditions())) {
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
	}

	@Override
	public List<SubjectInQuarter> getIncompleteSubjects(String id) {
		Student s = iStudentRepository.findById(id);
		List<String> subjects = s.getSubjects().stream().map(st -> st.getCode()).toList();
		List<SubjectInBlock> res = iSubjectInBlockRepository.getAllSubjectsInCurriculum(s.getCurriculum());
		List<Block> blocks = iBlockRepository.getBlocksByCurriculum(s.getCurriculum());
		List<SubjectInBlock> temp = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		for (Block i : blocks)
			map.put(i.getCode(), 0);
		for (SubjectInBlock sub : res) {
			String code = sub.getBlock().getCode();
			if (subjects.contains(sub.getCode())) {
				map.put(code, map.get(code) + sub.getSubject().getLabCredit() + sub.getSubject().getTheoryCredit());
			}
		}
		for (Block block : blocks) {
			if (map.get(block.getCode()) < block.getCredit()) {
				List<SubjectInBlock> res1 = res.stream().filter(b -> {
					try {
						if (b.getBlock().equals(block) && !subjects.contains(b.getCode())) {
							return true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return false;
				}).toList();
				temp.addAll(res1);
			}
		}

		List<SubjectInQuarter> subjectInQuarter = iSubjectInQuarterRepository.findByLastQuarter();
		List<Subject> temps = temp.size() == 0 ? new ArrayList<>()
				: temp.stream().map(SubjectInBlock::getSubject).toList();
		return subjectInQuarter.stream().filter(sq -> {
			try {
				if (temps.contains(sq.getSubject())) {
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}).toList();

	}

	@Override
	public List<SubjectInQuarter> getSubjectsCanLearn(String id) {
		Student s = iStudentRepository.findById(id);
		List<String> subjects = s.getSubjects().stream().map(Subject::getCode).toList();
		List<SubjectInBlock> res = iSubjectInBlockRepository.getAllSubjectsInCurriculum(s.getCurriculum());
		List<SubjectInBlock> temp = new ArrayList<>();
		List<Block> blocks = iBlockRepository.getBlocksByCurriculum(s.getCurriculum());
		Map<String, Integer> map = new HashMap<>();
		for (Block i : blocks)
			map.put(i.getCode(), 0);
		for (SubjectInBlock sub : res) {
			String code = sub.getBlock().getCode();
			if (subjects.contains(sub.getCode())) {
				map.put(code, map.get(code) + sub.getSubject().getLabCredit() + sub.getSubject().getTheoryCredit());
				temp.add(sub);
			}
		}
		for (Block block : blocks) {
			if (map.get(block.getCode()) >= block.getCredit()) {
				List<SubjectInBlock> res1 = res.stream().filter(b -> {
					try {
						if (b.getBlock().equals(block) && !subjects.contains(b.getCode())) {
							return true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					return false;
				}).toList();
				temp.addAll(res1);
			}
		}
		List<SubjectInQuarter> subjectInQuarter = iSubjectInQuarterRepository.findByLastQuarter();
		List<Subject> temps = temp.size() == 0 ? new ArrayList<>()
				: temp.stream().map(SubjectInBlock::getSubject).toList();
		return subjectInQuarter.stream().filter(sq -> {
			try {
				if (temps.contains(sq.getSubject())) {
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}).toList();
	}

	@Override
	public void setAsLeaved(String id) {
		try {
			Student s = iStudentRepository.findById(id);
			s.setStatus(Status.LEAVED);
			iStudentRepository.save(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Student> getStudentsByStatus(String string) {
		Status status = Status.INCOMPLETE;
		if (string.equalsIgnoreCase(Status.LEAVED.name())) {
			status = Status.LEAVED;
		} else if (string.equalsIgnoreCase(Status.GRADUATED.name())) {
			status = Status.GRADUATED;
		} else if (string.equalsIgnoreCase(Status.COMPLETED.name())) {
			status = Status.COMPLETED;
		}
		return iStudentRepository.findByStatus(status);
	}

	@Override
	public void setAsGraduated(String id) {
		try {
			Student s = iStudentRepository.findById(id);
			s.setStatus(Status.GRADUATED);
			iStudentRepository.save(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void importCertificate(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 2; i <= worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(1).getRawValue());
					Student s = getStudentByCode(code);
					List<Requirement> requirements = s.getConditions();
					if (s != null) {
						String ielts = row.getCell(3).getStringCellValue().trim();
						if (!ielts.isEmpty()) {
							Requirement r = iRequirementRepository.findByCode("IELTS 6.0");
							if (!requirements.contains(r)) {
								requirements.add(0, r);
							}
						}
						String met = row.getCell(4).getStringCellValue().trim();
						if (!met.isEmpty()) {
							Requirement r = iRequirementRepository.findByCode("MET 100");
							if (!requirements.contains(r)) {
								requirements.add(r);
							}
						}
						s.setConditions(requirements);
						iStudentRepository.save(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> getStatusByFilter(String generation, String department) {
		List<Student> st = iStudentRepository.findAll().stream().filter(s -> {
			boolean gen = false;
			boolean depart = false;
			try {
				if (!generation.isEmpty()) {
					if (s.getGeneration().getId().equals(generation)) {
						gen = true;
					}
				} else {
					gen = true;
				}
				if (!department.isEmpty()) {
					if (s.getCurriculum().getOrganization().getDepartment().getId().equals(department)) {
						depart = true;
					}
				} else {
					depart = true;
				}
				return depart && gen;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
		Integer undergraduated = 0;
		Integer waiting = 0;
		Integer graduated = 0;
		Integer leaved = 0;
		for (Student student : st) {
			if (student.getStatus().equals(Status.GRADUATED)) {
				graduated++;
			} else if (student.getStatus().equals(Status.LEAVED)) {
				leaved++;
			} else if (student.getStatus().equals(Status.COMPLETED)) {
				waiting++;
			} else {
				undergraduated++;
			}
		}
		List<Integer> result = new ArrayList<>();
		result.add(undergraduated);
		result.add(waiting);
		result.add(graduated);
		result.add(leaved);
		return result;

	}

	@Override
	public List<Integer> getCertificateByFilter(String generation, String department, String status) {
		List<Student> st = iStudentRepository.findAll().stream().filter(s -> {
			boolean gen = false;
			boolean depart = false;
			boolean sta = false;
			try {
				if (!generation.isEmpty()) {
					if (s.getGeneration().getId().equals(generation)) {
						gen = true;
					}
				} else {
					gen = true;
				}
				if (!department.isEmpty()) {
					if (s.getCurriculum().getOrganization().getDepartment().getId().equals(department)) {
						depart = true;
					}
				} else {
					depart = true;
				}
				if (!status.isEmpty()) {
					if (s.getStatus().name().equalsIgnoreCase(status)) {
						sta = true;
					}
				} else {
					sta = true;
				}
				return depart && gen && sta;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());
		Integer qp = 0;
		Integer credit = 0;
		Integer ielts = 0;
		Requirement ieltsRequirement = iRequirementRepository.findByCode("IELTS 6.0");
		Requirement qpRequirement = iRequirementRepository.findByCode("MET 100");
		for (Student student : st) {
			if (!student.getConditions().contains(ieltsRequirement)) {
				ielts++;

			}
			if (!student.getConditions().contains(qpRequirement)) {
				qp++;
			}
			if (student.getActualCredit() < student.getCurriculum().getCredit()) {
				credit++;
			}
		}
		List<Integer> result = new ArrayList<>();
		result.add(ielts);
		result.add(qp);
		result.add(credit);
		return result;

	}

	@Override
	public List<List<Integer>> getTotalStudent() {
		List<Integer> bbs = new ArrayList<>();
		List<Integer> cit = new ArrayList<>();
		List<Integer> set = new ArrayList<>();
		List<Integer> nursing = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		List<Generation> gen = iGenerationRepository.findAll();

		for (Generation generation : gen) {
			Integer b = 0;
			Integer c = 0;
			Integer s = 0;
			Integer n = 0;
			List<Student> students = iStudentRepository.findByGeneration(generation);
			for (Student st : students) {
				String code = st.getCurriculum().getOrganization().getDepartment().getCode();
				if (code.equalsIgnoreCase("BBS")) {
					b++;
				} else if (code.equalsIgnoreCase("CIT")) {
					c++;
				} else if (code.equalsIgnoreCase("NURS")) {
					n++;
				} else if (code.equalsIgnoreCase("SET")) {
					s++;
				}
			}
			bbs.add(b);
			cit.add(c);
			nursing.add(n);
			set.add(s);
		}
		result.add(bbs);
		result.add(cit);
		result.add(set);
		result.add(nursing);
		return result;

	}

	@Override
	public List<SubjectInQuarter> getTimeTable(String quarter, String year, String student) {
		Student s = iStudentRepository.findById(student);
		Year y = iYearRepository.findById(year);
		Quarter q = iQuarterRepository.findQuarterByQuarterYear(quarter, y);
		Result r = iResultRepository.getResultByStudentAndQuarter(s, q);
		List<SubjectInQuarter> subjects = new ArrayList<>();
		if (r != null) {
			subjects = r.getEnrollments().stream().map(Enrollment::getSubject).collect(Collectors.toList());
		}
		return subjects;
	}

	@Override
	public CreditResponse getCredits(String id) {
		Student s = iStudentRepository.findById(id);
		List<SubjectInBlock> res = iSubjectInBlockRepository.getAllSubjectsInCurriculum(s.getCurriculum());
		List<String> subjects = s.getSubjects().stream().map(Subject::getCode).toList();
		List<Block> blocks = iBlockRepository.getBlocksByCurriculum(s.getCurriculum());
		Map<String, Integer> blockCredit = new HashMap<>();
		Map<String, Integer> completeBlock = new HashMap<>();
		Map<String, Boolean> electiveBlock = new HashMap<>();
		CreditResponse response = new CreditResponse();
		response.setCompleteCredit(s.getActualCredit());
		response.setCredit(s.getCurriculum().getCredit());
		for (Block i : blocks) {
			blockCredit.put(i.getCode(), i.getCredit());
			electiveBlock.put(i.getCode(), false);
			completeBlock.put(i.getCode(), 0);
		}
		for (SubjectInBlock sub : res) {
			String code = sub.getBlock().getCode();
			Integer credit = sub.getSubject().getLabCredit() + sub.getSubject().getTheoryCredit();
			if (sub.getMandatory().equals(Mandatory.ELECTIVE)) {
				if (subjects.contains(sub.getCode())) {
					if (blockCredit.get(code) > completeBlock.get(code)) {
						if (blockCredit.get(code) >= completeBlock.get(code) + credit) {
							completeBlock.put(code, completeBlock.get(code) + credit);
							response.setCompleteElective(response.getCompleteElective() + credit);
						} else {
							response.setCompleteElective(
									response.getCompleteElective() + blockCredit.get(code) - completeBlock.get(code));
							completeBlock.put(code, blockCredit.get(code));
						}
					}
				}
				if (!electiveBlock.get(code)) {
					response.setElective(response.getElective() + blockCredit.get(code));
					electiveBlock.put(code, true);
				}
			} else {
				if (subjects.contains(sub.getCode())) {
					response.setCompleteCompulsory(response.getCompleteCompulsory() + credit);
				}
				if (!electiveBlock.get(code)) {
					response.setCompulsory(response.getCompulsory() + blockCredit.get(code));
					electiveBlock.put(code, true);
				}
			}

		}
		return response;
	}

}
