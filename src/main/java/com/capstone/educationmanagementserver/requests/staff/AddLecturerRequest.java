package com.capstone.educationmanagementserver.requests.staff;

import java.util.Date;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Organization;

import lombok.Data;

@Data
public class AddLecturerRequest {
	String code;
	String lastName;
	String middleName;
	String firstName;
	String email;
	String gender;
	Date doB;
	Organization department;
}
