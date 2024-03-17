package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Lecturer;

public interface ILecturerRepository extends RepositoryManager<Lecturer> {
	List<Lecturer> findByDepartment(String department);

	Lecturer findByCode(String code);

	Lecturer findByEmail(String email);
}
