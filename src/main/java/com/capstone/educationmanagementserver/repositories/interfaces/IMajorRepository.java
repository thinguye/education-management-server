package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.models.Major;

public interface IMajorRepository extends RepositoryManager<Major>{

	List<Major> findByDepartment(Department department);

	Major findByCode(String code);

}
