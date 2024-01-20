package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.IElectiveGroupRepository;
import com.capstone.educationmanagementserver.requests.elective.AddElectiveGroupRequest;
import com.capstone.educationmanagementserver.requests.elective.UpdateElectiveGroupRequest;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ElectiveGroupService implements IElectiveGroupService {
	@Autowired
	private IElectiveGroupRepository iElectiveGroupRepository;

	@Override
	public void addElectiveGroupFromFile(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addElectiveGroup(AddElectiveGroupRequest request) {
		ElectiveGroup e = ElectiveGroup.builder().name(request.getName()).credit(request.getCredit()).build();
		iElectiveGroupRepository.save(e);
	}

	@Override
	public void updateElectiveGroup(UpdateElectiveGroupRequest request) {
		ElectiveGroup e = iElectiveGroupRepository.findById(request.getId());
		e.setCredit(request.getCredit());
		e.setName(request.getName());
		iElectiveGroupRepository.save(e);
	}

	@Override
	public void removeElectiveGroup(String id) {
		ElectiveGroup e = iElectiveGroupRepository.findById(id);
		iElectiveGroupRepository.remove(e);

	}

	@Override
	public List<ElectiveGroup> getAllElectiveGroup() {
		return iElectiveGroupRepository.findAll();
	}

	@Override
	public ElectiveGroup getElectiveGroupById(String id) {
		return iElectiveGroupRepository.findById(id);
	}

	@Override
	public ElectiveGroup getElectiveGroupByName(String name) {
		return iElectiveGroupRepository.findByName(name);
	}

}
