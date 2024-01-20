package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.requests.student.AddStudentRequest;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IStudentService {
	void addStudent(AddStudentRequest student);

	void updateStudentProfile(UpdateStudentProfileRequest student);

	void removeStudent(String id);

	List<Student> getAllStudent();

	Student getStudentByCode(String code);

	Student getStudentById(String id);

	Student getStudentByEmail(String email);

	void addStudentFromFile(MultipartFile file);
}
