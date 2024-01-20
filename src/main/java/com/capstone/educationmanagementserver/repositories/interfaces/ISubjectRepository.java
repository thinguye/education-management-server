package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Subject;

public interface ISubjectRepository extends RepositoryManager<Subject> {

	Subject findByName(String name);

	Subject findByCode(String code);

}
