package com.capstone.educationmanagementserver.requests.student;

import java.util.Date;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Department;

import lombok.Data;

@Data
public class UpdateStudentProfileRequest {
	String id;
	String firstName;
	String middleName;
	String lastName;
	String code;
	String email;
	Gender gender;
	Major department;
	Generation generation;
	Date dateOfBirth;
}
