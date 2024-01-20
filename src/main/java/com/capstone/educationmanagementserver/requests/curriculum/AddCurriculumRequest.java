package com.capstone.educationmanagementserver.requests.curriculum;

import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;

import lombok.Data;

@Data
public class AddCurriculumRequest {
	String code;
	String name;
	Organization department;
	Generation generation;
}
