package com.capstone.educationmanagementserver.requests.requirement;

import lombok.Data;

@Data
public class CreateRequirementRequest {
	String code;
	String name;
	String status;
}
