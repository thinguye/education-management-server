package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.requests.elective.AddElectiveGroupRequest;
import com.capstone.educationmanagementserver.requests.elective.UpdateElectiveGroupRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IElectiveGroupService {
	void addElectiveGroupFromFile(MultipartFile file);

	void addElectiveGroup(AddElectiveGroupRequest request);

	void updateElectiveGroup(UpdateElectiveGroupRequest request);

	void removeElectiveGroup(String id);

	List<ElectiveGroup> getAllElectiveGroup();

	ElectiveGroup getElectiveGroupById(String id);

	ElectiveGroup getElectiveGroupByName(String name);
}
