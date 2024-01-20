package com.capstone.educationmanagementserver.requests.subject;

import lombok.Data;

@Data
public class UpdateSubjectRequest {
	String id;
	String code;
	String name;
}
