package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;

public interface ISubjectInQuarterRepository extends RepositoryManager<SubjectInQuarter> {

	SubjectInQuarter findBySubjectQuarter(Subject s, Quarter q);

	List<SubjectInQuarter> findByLecturer(Lecturer lecturer);

	List<SubjectInQuarter> findByLecturerLastQuarter(Lecturer l);

	List<SubjectInQuarter> findBySubjectLecturer(Lecturer l, Subject s);

	List<SubjectInQuarter> findByLastQuarter();

}
