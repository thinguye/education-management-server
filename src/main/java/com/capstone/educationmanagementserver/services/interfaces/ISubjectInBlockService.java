package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.SubjectInBlock;

public interface ISubjectInBlockService {
	List<SubjectInBlock> getBlocksByCurriculum(String id);

	List<SubjectInBlock> getSubjectsByCurriculum(String id);

	List<SubjectInBlock> getAll();
}
