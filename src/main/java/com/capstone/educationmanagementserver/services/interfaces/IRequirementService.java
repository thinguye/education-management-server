package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Requirement;
import com.capstone.educationmanagementserver.requests.requirement.CreateRequirementRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IRequirementService {
	List<Requirement> getAllRequirement();
	void createRequirement(CreateRequirementRequest request);
	void createRequirementFromFile(MultipartFile file);
}
