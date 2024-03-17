package com.capstone.educationmanagementserver.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("result")
public class Result {
	@Id
	String id;
	@DBRef
	Student student;
	Quarter quarter;
	@DBRef
	List<Enrollment> enrollments;
	Integer credit;
	Integer totalCredit;
	Double gpa;

	@Builder
	public Result(Student student, Quarter quarter, List<Enrollment> enrollments, Integer totalCredit) {
		this.student = student;
		this.quarter = quarter;
		this.enrollments = enrollments;
		this.credit = 0;
		this.totalCredit = totalCredit;
		this.gpa = 0.00;
	}

}
