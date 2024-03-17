package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInBlock;


public interface ISubjectInBlockRepository extends RepositoryManager<SubjectInBlock>{
	List<SubjectInBlock> getAllSubjectsInBlock(Block block);

	List<SubjectInBlock> getAllSubjectsInCurriculum(Curriculum c);

	boolean isExistInCurriculum(Curriculum c, Subject subject);

	Block findBySubject(Subject subject, Curriculum curriculum);

	List<SubjectInBlock> getSubjectsInCurriculumByMandatory(Curriculum c, Mandatory m);
}
