package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ICurriculumService {
	void addCirriculum(AddCurriculumRequest request);

	void removeCirriculum(String id);

	Curriculum getCirriculumById(String id);

	List<Curriculum> getCurriculumsByGeneration(Generation generation);

	List<Curriculum> getCurriculumsByOrganization(Department organization);

	List<Curriculum> getAllCirriculums();

	Curriculum getCirriculumByOrganization(Major organization, Generation generation);

	void addCurriculumFromFile(MultipartFile file);

	List<Curriculum> getCirriculumByDepartment(String id);

}
