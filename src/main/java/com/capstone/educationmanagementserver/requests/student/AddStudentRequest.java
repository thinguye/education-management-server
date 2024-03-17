package com.capstone.educationmanagementserver.requests.student;

import java.util.Date;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Department;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;

import org.apache.commons.math3.analysis.function.Add;

import lombok.Builder;
import lombok.Data;

@Data
public class AddStudentRequest {
	String code;
	String firstName;
	String middleName;
	String lastName;
	String gender;
	String email;
	Major department;
	Generation generation;
	Date dateOfBirth;

	public AddStudentRequest(String code, String firstName, String middleName, String lastName, String gender,
			String email, Major department, Generation generation, Date dateOfBirth) {
		this.code = code;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.department = department;
		this.generation = generation;
		this.dateOfBirth = dateOfBirth;
	}
}
