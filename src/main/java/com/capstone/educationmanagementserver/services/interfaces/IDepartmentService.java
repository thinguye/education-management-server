package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.requests.department.AddDepartmentRequest;
import com.capstone.educationmanagementserver.requests.department.UpdateDepartmentRequest;

public interface IDepartmentService {

	void createDepartment(AddDepartmentRequest addDepartmentRequest);

	void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest);

	void deleteById(String id);

	List<Department> getAllDepartments();

	Department getDepartmentById(String id);

	List<Department> getDepartmentsBySchool(String school);

	Department getDepartmentByCode(String code);
}
