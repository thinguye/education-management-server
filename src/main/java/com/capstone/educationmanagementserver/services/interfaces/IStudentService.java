package com.capstone.educationmanagementserver.services.interfaces;

import java.util.Dictionary;
import java.util.List;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.models.TimeTable;
import com.capstone.educationmanagementserver.requests.response.Undergraduate;
import com.capstone.educationmanagementserver.requests.student.AddStudentRequest;
import com.capstone.educationmanagementserver.requests.student.CreditResponse;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IStudentService {
	void addStudent(AddStudentRequest student);

	void updateStudentProfile(UpdateStudentProfileRequest student);

	void removeStudent(String id);

	List<Student> getAllStudent();

	Student getStudentByCode(String code);

	Student getStudentById(String id);

	Student getStudentByEmail(String email);

	void addStudentFromFile(MultipartFile file);

	List<Student> getStudentGraduated();

	Undergraduate getStudentUndergraduated();

	List<Student> getListStudentUnderGraduate();

	List<SubjectInQuarter> getIncompleteSubjects(String id);

	List<SubjectInQuarter> getSubjectsCanLearn(String id);

	void setAsLeaved(String id);

	List<Student> getStudentsByStatus(String string);

	void setAsGraduated(String id);

	void importCertificate(MultipartFile file);

	List<Integer> getStatusByFilter(String generation, String department);

	List<Integer> getCertificateByFilter(String generation, String department, String status);

	List<List<Integer>> getTotalStudent();

	List<SubjectInQuarter> getTimeTable(String quarter, String year, String student);

	CreditResponse getCredits(String id);
}
