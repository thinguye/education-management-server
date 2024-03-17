package com.capstone.educationmanagementserver.requests.department;

import com.capstone.educationmanagementserver.models.Department;

import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;

import lombok.Data;

@Data
public class UpdateDepartmentRequest {
	String id;
	String code;
	String name;
	@Nullable
	Department parent;
}
