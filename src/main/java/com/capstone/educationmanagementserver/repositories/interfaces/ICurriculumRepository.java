package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Department;

public interface ICurriculumRepository extends RepositoryManager<Curriculum> {
	List<Curriculum> getCurriculumsByGeneration(Generation generation);
	List<Curriculum> getCurriculumsByOrganization(Department organization);
	Curriculum getCurriculumByCode(String code);
	Curriculum findCurriculumByOrganization(Major organization, Generation generation);
}
