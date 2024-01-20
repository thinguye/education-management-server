package com.capstone.educationmanagementserver.requests.curriculum;

import com.capstone.educationmanagementserver.models.ElectiveGroup;

import lombok.Data;

@Data
public class AddElectivesRequest {
	String id;
	ElectiveGroup subject;
}
