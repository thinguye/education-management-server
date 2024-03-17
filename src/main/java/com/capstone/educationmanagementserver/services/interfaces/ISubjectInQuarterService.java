package com.capstone.educationmanagementserver.services.interfaces;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;
import com.capstone.educationmanagementserver.requests.quarter.AddSubjectToQuarter;
import com.capstone.educationmanagementserver.requests.response.NumberStudentResponse;

import org.springframework.web.multipart.MultipartFile;

public interface ISubjectInQuarterService {

	void addSubject(AddSubjectToQuarter request);

	List<SubjectInQuarter> getAll();

	SubjectInQuarter getBySubjectQuarter(String subject, String quarter, String year);

	void removeSubject(String id);

	SubjectInQuarter getSubjectById(String id);

	void addSubjectsFromFile(MultipartFile file);

	List<SubjectInQuarter> getByLecturer(String id);

	List<SubjectInQuarter> getTeachingSubjects(String id);

	List<Subject> getDistinctSubjects(String id);

	NumberStudentResponse getNoStudents(String id, String subject, String generation);

}
