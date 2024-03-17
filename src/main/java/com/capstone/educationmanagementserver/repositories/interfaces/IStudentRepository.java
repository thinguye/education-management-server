package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.enums.Status;
import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Student;

public interface IStudentRepository extends RepositoryManager<Student> {

	Student findByCode(String code);

	Student findByEmail(String email);

	List<Student> findStudentGraduated();

	List<Student> findStudentUndergraduated();

	List<Student> findByStatus(Status status);

	List<Student> findByGeneration(Generation generation);

}
