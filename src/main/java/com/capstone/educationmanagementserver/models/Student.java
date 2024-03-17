package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.enums.Status;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;

import org.apache.poi.hpsf.Array;
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
	Generation generation;
	Integer credit;
	Integer actualCredit;
	Double gpa;
	List<Requirement> conditions;
	List<Subject> subjects;
	Status status;

	@Builder
	public Student(String code, String firstName, String middleName, String lastName, Gender gender, String email,
			Date dateOfBirth, Curriculum curriculum, Generation generation) {
		this.code = code;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.curriculum = curriculum;
		this.generation = generation;
		this.gpa = 0.0;
		this.credit = 0;
		this.actualCredit = 0;
		this.conditions = new ArrayList<>();
		this.subjects = new ArrayList<>();
		this.status = Status.INCOMPLETE;
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
