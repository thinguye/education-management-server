package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("enrollment")
public class Enrollment {
	@Id
	String id;
	@DBRef
	Student student;
	@DBRef
	SubjectInQuarter subject;
	Double grade;
	String gradeLetter;

	@Builder
	public Enrollment(Student student, SubjectInQuarter subject) {
		this.student = student;
		this.subject = subject;
	}
}
