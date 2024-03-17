package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.repositories.interfaces.IDepartmentRepository;
import com.capstone.educationmanagementserver.requests.department.AddDepartmentRequest;
import com.capstone.educationmanagementserver.requests.department.UpdateDepartmentRequest;
import com.capstone.educationmanagementserver.services.interfaces.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService implements IDepartmentService {
	@Autowired
	private IDepartmentRepository iDepartmentRepository;

	@Override
	public void createDepartment(AddDepartmentRequest addDepartmentRequest) {
		Department department = Department.builder().code(addDepartmentRequest.getCode()).name(addDepartmentRequest.getName()).parent(addDepartmentRequest.getParent()).build();
		iDepartmentRepository.save(department);
	}

	@Override
	public void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest) {

	}

	@Override
	public void deleteById(String id) {
		Department department = iDepartmentRepository.findById(id);
		iDepartmentRepository.remove(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		return iDepartmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(String id) {
		return iDepartmentRepository.findById(id);
	}

	@Override
	public List<Department> getDepartmentsBySchool(String school) {
		return iDepartmentRepository.findBySchool(school);
	}

	@Override
	public Department getDepartmentByCode(String code) {
		return iDepartmentRepository.findByCode(code);
	}

}
