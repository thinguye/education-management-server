package com.capstone.educationmanagementserver.requests.student;

import java.util.Date;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;

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
	Organization department;
	Generation generation;
	Date dateOfBirth;
}
