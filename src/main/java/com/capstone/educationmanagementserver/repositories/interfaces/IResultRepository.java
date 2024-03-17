package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.Date;
import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Result;
import com.capstone.educationmanagementserver.models.Student;

public interface IResultRepository extends RepositoryManager<Result> {
	List<Result> getResultInQuarter(Quarter quarter);

	List<Result> getResultByStudent(Student student);

	Result getResultByEnrollment(Enrollment enrollment);

	Result getResultByStudentAndQuarter(Student student, Quarter quarter);

	Result getResultLastQuarterByStudent(Student st, Date date);
}
