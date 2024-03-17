package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("requirement")
public class Requirement {
	@Id
	String id;
	String code;
	String name;
	String status;

	@Builder
	public Requirement(String code, String name, String status) {
		this.code = code;
		this.name = name;
		this.status = status;
	}
}