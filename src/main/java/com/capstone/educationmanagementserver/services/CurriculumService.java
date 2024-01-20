package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IElectiveRepository;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddElectivesRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddSubjectToCurriculum;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateBlockRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumService implements ICurriculumService {
	@Autowired
	ICurriculumRepository iCirriculumRepository;
	@Autowired
	IElectiveService iElectiveService;

	@Override
	public void addCirriculum(AddCurriculumRequest request) {
		Curriculum c = Curriculum.builder().code(request.getCode()).name(request.getName())
				.organization(request.getDepartment()).generation(request.getGeneration()).build();
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
	public Curriculum getCirriculumByOrganization(Organization department, Generation generation) {
		return iCirriculumRepository.findCurriculumByOrganization(department, generation);
	}

	@Override
	public List<Curriculum> getCurriculumsByGeneration(Generation generation) {
		return iCirriculumRepository.getCurriculumsByGeneration(generation);
	}

	@Override
	public List<Curriculum> getCurriculumsByOrganization(Organization organization) {
		return iCirriculumRepository.getCurriculumsByOrganization(organization);
	}



}
