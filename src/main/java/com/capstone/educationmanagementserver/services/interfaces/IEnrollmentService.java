package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.requests.enrollment.AddEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UpdateEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;

public interface IEnrollmentService {

	List<Enrollment> getAllEnrollment();

	Enrollment getEnrollmentById(String id);

	void removeEnrollment(String id);

	void addEnrollment(AddEnrollmentRequest request);

	void updateEnrollment(UpdateEnrollmentRequest request);

	void addEnrollmentFromFile(UploadEnrollementRequest request);

	List<Enrollment> getStudentBySubject(String id);

	Enrollment getEnrollmentByStudentSubject(SubjectInQuarter s, Student st);

	List<Enrollment> getSubjectByStudent(String id);

}
