package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.requests.staff.AddLecturerRequest;
import com.capstone.educationmanagementserver.requests.staff.UpdateLecturerRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ILecturerService {
	List<Lecturer> getAllLecturer();

	Lecturer getLecturerById(String id);

	Lecturer getLecturerByCode(String code);

	void addLecturersFromFile(MultipartFile file);

	void addLecturer(AddLecturerRequest request);

	List<Lecturer> getLecturerByName(String name);

	void removeLecturer(String id);

	void updateLecturer(UpdateLecturerRequest request);

	Lecturer getLecturerByEmail(String email);
}
