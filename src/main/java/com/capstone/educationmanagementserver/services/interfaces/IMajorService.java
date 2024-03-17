package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.requests.major.AddMajorRequest;

public interface IMajorService {

	List<Major> getAllMajor();

	Major getMajorById(String id);

	void removeMajor(String id);

	List<Major> getMajorByDepartment(String id);

	void createMajor(AddMajorRequest request);

}
