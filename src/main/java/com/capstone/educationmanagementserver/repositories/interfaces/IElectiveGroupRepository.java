package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.ElectiveGroup;

public interface IElectiveGroupRepository extends RepositoryManager<ElectiveGroup>{

	ElectiveGroup findByName(String name);

}
