package com.capstone.educationmanagementserver.repositories.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.general.RepositoryManager;
import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;

public interface IEnrollmentRepository extends RepositoryManager<Enrollment> {

	List<Enrollment> findBySubject(SubjectInQuarter s);

	Enrollment findByStudentSubject(SubjectInQuarter s, Student st);

	List<Enrollment> findSubjectByStudent(Student student);
	

}
