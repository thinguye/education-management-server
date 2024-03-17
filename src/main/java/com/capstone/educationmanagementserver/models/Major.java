package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("major")
public class Major {
	@Id
	String id;
	@Indexed(unique = true)
	String code;
	String name;
	Department department;

	@Builder
	public Major(String code, String name, Department department) {
		this.code = code;
		this.name = name;
		this.department = department;
	}
}
