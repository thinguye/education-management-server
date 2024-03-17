package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.repositories.interfaces.IDepartmentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IMajorRepository;
import com.capstone.educationmanagementserver.requests.major.AddMajorRequest;
import com.capstone.educationmanagementserver.services.interfaces.IMajorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService implements IMajorService {
	@Autowired
	private IMajorRepository iMajorRepository;
	@Autowired
	private IDepartmentRepository iDepartmentRepository;

	@Override
	public void createMajor(AddMajorRequest request) {
		Major major = Major.builder().code(request.getCode()).name(request.getName()).department(request.getParent())
				.build();
		iMajorRepository.save(major);
	}

	@Override
	public List<Major> getAllMajor() {
		List<Major> majors = iMajorRepository.findAll();
		return majors;
	}

	@Override
	public Major getMajorById(String id) {
		return iMajorRepository.findById(id);
	}

	@Override
	public void removeMajor(String id) {
		Major major = iMajorRepository.findById(id);
		iMajorRepository.remove(major);
	}

	@Override
	public List<Major> getMajorByDepartment(String id) {
		Department department = iDepartmentRepository.findById(id);
		return iMajorRepository.findByDepartment(department);
	}
}
