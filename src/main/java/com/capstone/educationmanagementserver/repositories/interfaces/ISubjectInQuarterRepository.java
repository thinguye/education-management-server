package com.capstone.educationmanagementserver.repositories.interfaces;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;

public interface ISubjectInQuarterRepository extends RepositoryManager<SubjectInQuarter> {

	SubjectInQuarter findBySubjectQuarter(Subject s, Quarter q);

}
