package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.List;

import com.capstone.educationmanagementserver.models.Requirement;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.repositories.interfaces.IRequirementRepository;
import com.capstone.educationmanagementserver.requests.requirement.CreateRequirementRequest;
import com.capstone.educationmanagementserver.services.interfaces.IRequirementService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class RequirementService implements IRequirementService{
@Autowired
private IRequirementRepository iRequirementRepository;

@Override
public List<Requirement> getAllRequirement() {
	return iRequirementRepository.findAll();
}

@Override
public void createRequirement(CreateRequirementRequest request) {
	String code = request.getCode();
	String name = request.getName();
	String status = request.getStatus();
	Requirement requirement = Requirement.builder().code(code).name(name).status(status).build();
	iRequirementRepository.save(requirement);
}

@Override
public void createRequirementFromFile(MultipartFile file) {
	try {
		InputStream inputStream = file.getInputStream();
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
			XSSFSheet worksheet = workbook.getSheetAt(0);
			for (int i = 2; i < worksheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = worksheet.getRow(i);
				String code = String.valueOf(row.getCell(1).getRawValue());
				String name = String.valueOf(row.getCell(2).getRawValue());
				String status = String.valueOf(row.getCell(3).getRawValue());
				Requirement requirement = Requirement.builder().code(code).name(name).status(status).build();
				iRequirementRepository.save(requirement);
			}
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
}


}
