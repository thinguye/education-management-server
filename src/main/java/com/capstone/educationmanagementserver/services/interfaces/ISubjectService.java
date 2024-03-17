package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.requests.subject.AddSubjectRequest;
import com.capstone.educationmanagementserver.requests.subject.UpdateRequirements;
import com.capstone.educationmanagementserver.requests.subject.UpdateSubjectRequest;

import org.springframework.web.multipart.MultipartFile;

public interface ISubjectService {
	void addSubject(AddSubjectRequest request);

	void updateSubject(UpdateSubjectRequest request);

	void addSubjectFromFile(MultipartFile file);

	List<Subject> getAllSubject();

	Subject getSubjectById(String id);

	Subject getSubjectByCode(String code);

	Subject getSubjectByName(String name);

	void removeSubject(String id);

	void updatePrerequire(UpdateRequirements request);

	List<Subject> courses(String temp);
}
