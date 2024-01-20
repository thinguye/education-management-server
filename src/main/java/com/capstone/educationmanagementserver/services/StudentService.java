package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.List;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.repositories.interfaces.IStudentRepository;
import com.capstone.educationmanagementserver.requests.student.AddStudentRequest;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;
import com.capstone.educationmanagementserver.services.interfaces.IStudentService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentService implements IStudentService {
	@Autowired
	IStudentRepository iStudentRepository;
	@Autowired
	ICurriculumService iCurriculumService;

	@Override
	public void addStudent(AddStudentRequest student) {
		Student st = Student.builder().code(student.getCode()).gender(Gender.valueOf(student.getGender()))
				.firstName(student.getFirstName()).lastName(student.getLastName()).middleName(student.getMiddleName())
				.email(student.getEmail()).dateOfBirth(student.getDateOfBirth()).build();
		Curriculum c = iCurriculumService.getCirriculumByOrganization(student.getDepartment(), student.getGeneration());
		st.setCurriculum(c);
		iStudentRepository.save(st);
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
				for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
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
						Student tempStudent = Student.builder().code(code).lastName(lastName).middleName(middleName)
								.firstName(firstName).gender(gender).email(email).build();
						iStudentRepository.save(tempStudent);
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
