package com.capstone.educationmanagementserver.requests.curriculum;

import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Organization;

import lombok.Data;
@Data
public class UpdateCirriculumRequest {
	String id;
	String code;
	String name;
	Integer credit;
	Organization organization;
	Generation generation;
}
