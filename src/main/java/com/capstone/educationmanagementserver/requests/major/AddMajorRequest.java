package com.capstone.educationmanagementserver.requests.major;

import com.capstone.educationmanagementserver.models.Department;

import lombok.Data;

@Data
public class AddMajorRequest {
	String code;
	String name;
	Department parent;
}
