package com.capstone.educationmanagementserver.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectRepository;
import com.capstone.educationmanagementserver.requests.subject.AddSubjectRequest;
import com.capstone.educationmanagementserver.requests.subject.UpdateRequirements;
import com.capstone.educationmanagementserver.requests.subject.UpdateSubjectRequest;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SubjectService implements ISubjectService {
	@Autowired
	private ISubjectRepository iSubjectRepository;

	@Override
	public List<Subject> courses(String temp) {
		List<Subject> subjects = new ArrayList<>();
		if (temp.trim().length() > 0 || temp.isEmpty()) {
			if (temp.contains(";")) {
				String[] codes = temp.split(";");
				for (String preCode : codes) {
					subjects.add(iSubjectRepository.findByCode(preCode));
				}
			} else {
				subjects.add(iSubjectRepository.findByCode(temp.trim()));
			}
		}
		return subjects;
	}

	@Override
	public void addSubjectFromFile(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				for (int i = 4; i < worksheet.getPhysicalNumberOfRows(); i++) {
					XSSFRow row = worksheet.getRow(i);
					String code = String.valueOf(row.getCell(0).getStringCellValue());
					if (iSubjectRepository.findByCode(code) == null) {
						String name = row.getCell(1).getStringCellValue();
						Integer theoryCredit = Integer.parseInt(row.getCell(2).getRawValue());
						Integer labCredit = Integer.parseInt(row.getCell(3).getRawValue());
						List<Subject> previousCourses = (row.getCell(4).getStringCellValue().trim().equals("")
								? new ArrayList<>()
								: courses(row.getCell(4).getStringCellValue().trim()));
						List<Subject> prerequistes = (row.getCell(5).getStringCellValue().trim().equals("") ? new ArrayList<>()
								: courses(row.getCell(5).getStringCellValue().trim()));
						Subject tempSubject = Subject.builder().code(code).name(name).theoryCredit(theoryCredit)
								.labCredit(labCredit).prerequisites(prerequistes).previousCourses(previousCourses)
								.build();
						iSubjectRepository.save(tempSubject);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addSubject(AddSubjectRequest request) {
		Subject s = Subject.builder().code(request.getCode()).name(request.getName())
				.theoryCredit(request.getTheoryCredit()).labCredit(request.getLabCredit()).build();
		iSubjectRepository.save(s);

	}

	@Override
	public void updateSubject(UpdateSubjectRequest request) {
		Subject s = iSubjectRepository.findById(request.getId());
		s.setCode(request.getCode());
		s.setName(request.getName());
		iSubjectRepository.save(s);

	}

	@Override
	public List<Subject> getAllSubject() {
		return iSubjectRepository.findAll();
	}

	@Override
	public Subject getSubjectById(String id) {
		return iSubjectRepository.findById(id);
	}

	@Override
	public Subject getSubjectByCode(String code) {
		return iSubjectRepository.findByCode(code);
	}

	@Override
	public Subject getSubjectByName(String name) {
		return iSubjectRepository.findByName(name);
	}

	@Override
	public void removeSubject(String id) {
		Subject s = iSubjectRepository.findById(id);
		iSubjectRepository.remove(s);
	}

	@Override
	public void updatePrerequire(UpdateRequirements request) {
		Subject s = iSubjectRepository.findById(request.getId());
		s.setPrerequisites(request.getSubjects());
		iSubjectRepository.save(s);
	}

}
