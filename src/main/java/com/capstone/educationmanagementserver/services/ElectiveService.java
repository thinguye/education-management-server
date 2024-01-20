package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.repositories.interfaces.IElectiveRepository;
import com.capstone.educationmanagementserver.requests.elective.AddElectiveRequest;
import com.capstone.educationmanagementserver.requests.elective.UpdateElectiveRequest;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveGroupService;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ElectiveService implements IElectiveService {
	@Autowired
	private IElectiveRepository iElectiveRepository;
	@Autowired
	private IElectiveGroupService iElectiveGroupService;

	@Override
	public void addElectiveFromFile(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addElective(AddElectiveRequest request) {
		ElectiveGroup eGroup = iElectiveGroupService.getElectiveGroupById(request.getId());
		ElectiveSubject e = ElectiveSubject.builder().subject(request.getSubject()).electiveGroup(eGroup).build();
		iElectiveRepository.save(e);
	}

	@Override
	public void updateElective(UpdateElectiveRequest request) {
		ElectiveSubject e = iElectiveRepository.findById(request.getId());
		e.setElectiveGroup(request.getElectiveGroup());
		e.setSubject(request.getSubject());
		iElectiveRepository.save(e);
	}

	@Override
	public void removeElective(String id) {
		ElectiveSubject e = iElectiveRepository.findById(id);
		iElectiveRepository.remove(e);

	}

	@Override
	public List<ElectiveSubject> getByGroup(String id) {
		ElectiveGroup e = iElectiveGroupService.getElectiveGroupById(id);
		return iElectiveRepository.findByGroup(e);
	}

	@Override
	public List<ElectiveSubject> getAllElective() {
		return iElectiveRepository.findAll();
	}

	@Override
	public ElectiveSubject getElectiveById(String id) {
		return iElectiveRepository.findById(id);
	}

	@Override
	public ElectiveSubject getElectiveByName(String name) {
		return iElectiveRepository.findByName(name);
	}

}
