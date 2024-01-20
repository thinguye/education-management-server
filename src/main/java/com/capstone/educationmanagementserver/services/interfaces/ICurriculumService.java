package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddElectivesRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddSubjectToCurriculum;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateBlockRequest;

public interface ICurriculumService {
	void addCirriculum(AddCurriculumRequest request);

	void removeCirriculum(String id);

	Curriculum getCirriculumById(String id);

	List<Curriculum> getCurriculumsByGeneration(Generation generation);

	List<Curriculum> getCurriculumsByOrganization(Organization organization);

	List<Curriculum> getAllCirriculums();

	Curriculum getCirriculumByOrganization(Organization organization, Generation generation);

}
