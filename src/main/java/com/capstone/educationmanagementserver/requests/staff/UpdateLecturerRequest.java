package com.capstone.educationmanagementserver.requests.staff;

import com.capstone.educationmanagementserver.enums.Gender;
import com.capstone.educationmanagementserver.models.Organization;

import lombok.Data;

@Data
public class UpdateLecturerRequest {
	String id;
	String code;
	String lastName;
	String middleName;
	String firstName;
	String email;
	Gender gender;
	Organization organization;
}
