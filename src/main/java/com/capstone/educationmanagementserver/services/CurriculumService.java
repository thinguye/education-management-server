package com.capstone.educationmanagementserver.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Requirement;
import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.repositories.interfaces.IBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IMajorRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IRequirementRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInBlockRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectRepository;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;
import com.capstone.educationmanagementserver.services.interfaces.IDepartmentService;
import com.capstone.educationmanagementserver.services.interfaces.IGenerationService;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CurriculumService implements ICurriculumService {
	@Autowired
	ICurriculumRepository iCirriculumRepository;
	@Autowired
	private IDepartmentService iDepartmentService;
	@Autowired
	private IGenerationService iGenerationService;
	@Autowired
	private IBlockRepository iBlockRepository;
	@Autowired
	private ISubjectRepository iSubjectRepository;
	@Autowired
	private ISubjectInBlockRepository iSubjectInBlockRepository;
	@Autowired
	private IMajorRepository iMajorRepository;
	@Autowired
	private IRequirementRepository iRequirementRepository;

	@Override
	public void addCirriculum(AddCurriculumRequest request) {
		Curriculum c = Curriculum.builder().code(request.getCode()).name(request.getName())
				.organization(request.getDepartment()).generation(request.getGeneration()).credit(request.getCredit())
				.build();
		iCirriculumRepository.save(c);
	}

	@Override
	public void removeCirriculum(String id) {
		Curriculum cirriculum = iCirriculumRepository.findById(id);
		iCirriculumRepository.remove(cirriculum);

	}

	@Override
	public Curriculum getCirriculumById(String id) {
		return iCirriculumRepository.findById(id);
	}

	@Override
	public List<Curriculum> getAllCirriculums() {
		return iCirriculumRepository.findAll();
	}

	@Override
	public Curriculum getCirriculumByOrganization(Major department, Generation generation) {
		return iCirriculumRepository.findCurriculumByOrganization(department, generation);
	}

	@Override
	public List<Curriculum> getCurriculumsByGeneration(Generation generation) {
		return iCirriculumRepository.getCurriculumsByGeneration(generation);
	}

	@Override
	public List<Curriculum> getCurriculumsByOrganization(Department organization) {
		return iCirriculumRepository.getCurriculumsByOrganization(organization);
	}

	@Override
	public void addCurriculumFromFile(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				XSSFSheet worksheet = workbook.getSheetAt(0);
				String[] listS = String.valueOf(worksheet.getRow(2).getCell(0).getStringCellValue()).split(" - ");
				String name = listS[0];
				String code = listS[1].concat(listS[2]);
				Major major = iMajorRepository.findByCode(listS[1]);
				List<Generation> generation = new ArrayList<>();
				generation.add(iGenerationService.getGenerationByCode(listS[2]));
				String tempS = String.valueOf(worksheet.getRow(3).getCell(0).getStringCellValue());
				Integer credit = Integer.valueOf(tempS.substring(0, tempS.indexOf(" ")));
				Curriculum tempC = iCirriculumRepository.getCurriculumByCode(code);
				List<Requirement> requirements = iRequirementRepository.findAll();
				if (tempC == null) {
					Curriculum c = Curriculum.builder().code(code).credit(credit).generation(generation)
							.organization(major).name(name).conditions(requirements).build();
					iCirriculumRepository.save(c);
				}
				tempC = iCirriculumRepository.getCurriculumByCode(code);
				int i = 4;
				int currentBlock = 1;
				String tempCode = code;
				Block block = null;
				List<SubjectInBlock> tempSubjects = new ArrayList<>();
				do {
					XSSFRow row = worksheet.getRow(i);
					String value = String.valueOf(row.getCell(0).getStringCellValue());
					if (value.matches(".*[0-9]$")) {
						Subject s = iSubjectRepository.findByCode(row.getCell(0).getStringCellValue());
						String mandatory = String.valueOf(row.getCell(2).getStringCellValue());
						Mandatory m = Mandatory.COMPULSORY;
						if (mandatory.equalsIgnoreCase("ELECTIVE")){
							m = Mandatory.ELECTIVE;
						}
						SubjectInBlock subject = SubjectInBlock.builder().subject(s).mandatory(m).block(block).build();
						iSubjectInBlockRepository.save(subject);
						tempSubjects.add(subject);

					} else if (!value.equalsIgnoreCase("Course")) {
						String blockCode = tempCode.concat(String.valueOf(currentBlock++));
						String blockName = value;
						Integer blockCredit = Integer.valueOf(row.getCell(1).getRawValue());
						String paraName = String.valueOf(row.getCell(2).getStringCellValue().trim());
						Block paraBlock = null;
						if (!paraName.equals("")) {
							paraBlock = iBlockRepository.getBlockByNameCurriculum(paraName, tempC);
						}
						block = Block.builder().code(blockCode).name(blockName).curriculum(tempC).credit(blockCredit).paraBlock(paraBlock).build();
						iBlockRepository.save(block);
					}
					i++;
				} while (i < worksheet.getPhysicalNumberOfRows());
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Curriculum> getCirriculumByDepartment(String id) {
		Department d = iDepartmentService.getDepartmentById(id);
		return iCirriculumRepository.getCurriculumsByOrganization(d);
	}


}
