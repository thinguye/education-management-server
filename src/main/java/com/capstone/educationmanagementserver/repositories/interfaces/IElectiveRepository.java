package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.models.ElectiveSubject;

public interface IElectiveRepository extends RepositoryManager<ElectiveSubject> {

	ElectiveSubject findByName(String name);

	List<ElectiveSubject> findByGroup(ElectiveGroup e);
	
}
