package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Department;

public interface IDepartmentRepository extends RepositoryManager<Department> {
	List<Department> findBySchool(String school);

	Department findByCode(String code);
}
