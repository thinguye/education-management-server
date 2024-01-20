package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;

public interface ICurriculumRepository extends RepositoryManager<Curriculum> {
	List<Curriculum> getCurriculumsByGeneration(Generation generation);
	Curriculum findCurriculumByOrganization(Organization department, Generation generation);
	List<Curriculum> getCurriculumsByOrganization(Organization organization);
}
