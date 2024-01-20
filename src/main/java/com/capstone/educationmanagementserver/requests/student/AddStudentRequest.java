package com.capstone.educationmanagementserver.requests.student;

import java.util.Date;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Organization;
import com.capstone.educationmanagementserver.models.Generation;

import lombok.Data;
@Data
public class AddStudentRequest {
	String code;
	String firstName;
	String middleName;
	String lastName;
	String gender;
	String email;
	Organization department;
	Generation generation;
	Date dateOfBirth;
}
