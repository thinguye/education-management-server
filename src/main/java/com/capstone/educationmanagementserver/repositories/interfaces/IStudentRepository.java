package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Student;

public interface IStudentRepository extends RepositoryManager<Student> {

	Student findByCode(String code);

	Student findByEmail(String email);

}
