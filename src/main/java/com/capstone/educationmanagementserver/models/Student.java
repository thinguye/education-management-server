package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("student")
public class Student {
	@Id
	String id;
	String code;
	String firstName;
	String middleName;
	String lastName;
	Gender gender;
	String email;
	Date dateOfBirth;
	@DBRef
	Curriculum curriculum;
	List<Subject> subjects = new ArrayList<>();
	Integer credit;

	@Builder
	public Student(String code, String firstName, String middleName, String lastName, Gender gender, String email,
			Date dateOfBirth) {
		this.code = code;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public void updateStudent(UpdateStudentProfileRequest request) {
		this.code = request.getCode();
		this.firstName = request.getFirstName();
		this.middleName = request.getMiddleName();
		this.lastName = request.getLastName();
		this.gender = request.getGender();
		this.email = request.getEmail();
		this.dateOfBirth = request.getDateOfBirth();
	}
}
