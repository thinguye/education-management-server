package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capstone.educationmanagementserver.controllers.AuthController;
import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.IDepartmentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ILecturerRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;
import com.capstone.educationmanagementserver.requests.auth.SignupRequest;
import com.capstone.educationmanagementserver.requests.staff.AddLecturerRequest;
import com.capstone.educationmanagementserver.requests.staff.UpdateLecturerRequest;
import com.capstone.educationmanagementserver.services.interfaces.ILecturerService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LecturerService implements ILecturerService {
	@Autowired
	ILecturerRepository iLecturerRepository;
	@Autowired
	AuthController authController;
	@Autowired
	IDepartmentRepository iDepartmentRepository;
	@Autowired
	ISubjectInQuarterRepository iSubjectInQuarterRepository;
	
	
	@Override
	public List<Lecturer> getAllLecturer() {
		return iLecturerRepository.findAll();
	}

	@Override
	public Lecturer getLecturerById(String id) {
		return iLecturerRepository.findById(id);
	}

	@Override
	public Lecturer getLecturerByCode(String code) {
		return iLecturerRepository.findByCode(code);
	}

	@Override
	public void addLecturersFromFile(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(1).getStringCellValue());
					Lecturer s = getLecturerByCode(code);
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
						String department = row.getCell(7).getStringCellValue();
						Department d = iDepartmentRepository.findByCode(department);
						Lecturer tempStudent = Lecturer.builder().code(code).lastName(lastName).middleName(middleName)
								.firstName(firstName).gender(gender).email(email).department(d).build();
						iLecturerRepository.save(tempStudent);
						Set<String> roles = new HashSet<>();
						roles.add("Lecturer");
						String password = email.substring(0, email.indexOf("@")) + "@123";
						authController.registerUser(new SignupRequest(firstName, lastName, email, roles, password));
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
	public void addLecturer(AddLecturerRequest request) {
		Lecturer lec = getLecturerByCode(request.getCode());
		if (lec == null) {
			Lecturer l = Lecturer.builder().code(request.getCode()).lastName(request.getLastName())
					.middleName(request.getMiddleName()).firstName(request.getFirstName()).email(request.getEmail())
					.gender(Gender.valueOf(request.getGender())).department(request.getDepartment()).build();
			iLecturerRepository.save(l);
			Set<String> roles = new HashSet<>();
			roles.add("Lecturer");
			String password = request.getEmail().substring(0, request.getEmail().indexOf("@")) + "@123";
			authController.registerUser(new SignupRequest(request.getFirstName(), request.getLastName(),
					request.getEmail(), roles, password));
		}
	}

	@Override
	public List<Lecturer> getLecturerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLecturer(String id) {
		Lecturer l = iLecturerRepository.findById(id);
		iLecturerRepository.remove(l);

	}

	@Override
	public void updateLecturer(UpdateLecturerRequest request) {
		Lecturer l = iLecturerRepository.findById(request.getId());
		Lecturer l2 = iLecturerRepository.findByCode(request.getCode());
		if (l2 == null) {
			l.setCode(request.getCode());
		}
		l.setEmail(request.getEmail());
		l.setOrganization(request.getOrganization());
		l.setFirstName(request.getFirstName());
		l.setLastName(request.getLastName());
		l.setMiddleName(request.getMiddleName());
		iLecturerRepository.save(l);
	}

	@Override
	public Lecturer getLecturerByEmail(String email) {
		return iLecturerRepository.findByEmail(email);
	}



}
