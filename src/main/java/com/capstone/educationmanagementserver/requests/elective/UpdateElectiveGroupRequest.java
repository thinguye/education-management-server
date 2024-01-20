package com.capstone.educationmanagementserver.requests.elective;

import lombok.Data;
@Data
public class UpdateElectiveGroupRequest {
	String id;
	String name;
	Integer credit;
}
