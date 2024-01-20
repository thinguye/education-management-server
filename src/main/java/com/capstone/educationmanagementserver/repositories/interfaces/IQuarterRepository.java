package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Year;

public interface IQuarterRepository extends RepositoryManager<Quarter>{
	List<Quarter> findQuartersByYear(Year year);

	Quarter findQuarterByQuarterYear(String quarter, Year yearObject);
}
