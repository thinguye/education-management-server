package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Organization;
import com.capstone.educationmanagementserver.requests.department.AddDepartmentRequest;
import com.capstone.educationmanagementserver.requests.department.UpdateDepartmentRequest;

public interface IDepartmentService {

	void createDepartment(AddDepartmentRequest addDepartmentRequest);

	void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest);

	void deleteById(String id);

	List<Organization> getAllDepartments();

	Organization getDepartmentById(String id);

	List<Organization> getDepartmentsBySchool(String school);
}
