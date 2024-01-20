package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Year;

public interface IYearRepository extends RepositoryManager<Year> {

	Year findByCode(String code);

	Year findByName(String year);
	
}
