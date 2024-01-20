package com.capstone.educationmanagementserver.requests.elective;

import com.capstone.educationmanagementserver.models.ElectiveGroup;
import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;

@Data
public class UpdateElectiveRequest {
	String id;
	Subject subject;
	ElectiveGroup electiveGroup;
}
