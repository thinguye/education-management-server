package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Generation;

public interface IGenerationRepository extends RepositoryManager<Generation> {

	Generation findByCode(String code);

	Generation findByName(String name);

}
