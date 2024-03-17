package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Requirement;

public interface IRequirementRepository extends RepositoryManager<Requirement> {

	Requirement findByCode(String code);

}
