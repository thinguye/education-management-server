package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.ElectiveSubject;
import com.capstone.educationmanagementserver.requests.elective.AddElectiveRequest;
import com.capstone.educationmanagementserver.requests.elective.UpdateElectiveRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IElectiveService {
	void addElectiveFromFile(MultipartFile file);

	void addElective(AddElectiveRequest request);

	void updateElective(UpdateElectiveRequest request);

	void removeElective(String id);

	List<ElectiveSubject> getByGroup(String id);

	List<ElectiveSubject> getAllElective();

	ElectiveSubject getElectiveById(String id);

	ElectiveSubject getElectiveByName(String name);
}
