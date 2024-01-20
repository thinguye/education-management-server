package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Organization;

public interface IDepartmentRepository extends RepositoryManager<Organization> {
	List<Organization> findBySchool(String school);
}
