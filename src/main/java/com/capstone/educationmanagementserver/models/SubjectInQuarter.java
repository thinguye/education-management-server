package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("subjectInQuarter")
public class SubjectInQuarter {
	@Id
	String id;
	@DBRef
	Subject subject;
	@DBRef
	Quarter quarter;
	@DBRef
	Lecturer lecturer;
	Integer numberOfStudents;

	@Builder
	public SubjectInQuarter(Subject subject, Quarter quarter, Lecturer lecturer) {
		this.subject = subject;
		this.quarter = quarter;
		this.lecturer = lecturer;
		this.numberOfStudents = 0;
	}
}