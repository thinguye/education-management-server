package com.capstone.educationmanagementserver.requests.elective;

import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;

@Data
public class AddElectiveRequest {
	Subject subject;
	String id;
}
