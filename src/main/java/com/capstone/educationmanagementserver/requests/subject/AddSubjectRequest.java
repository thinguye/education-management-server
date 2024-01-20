package com.capstone.educationmanagementserver.requests.subject;

import java.util.List;

import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;

@Data
public class AddSubjectRequest {
	String code;
	String name;
	Integer theoryCredit;
	Integer labCredit;
	List<Subject> prerequisites;
	List<Subject> previousCourses;
}
