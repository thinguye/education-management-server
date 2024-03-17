package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("organization")
public class Department {
	@Id
	String id;
	@Indexed(unique = true)
	String code;
	String name;
	Department parent;

	@Builder
	public Department(String code, String name, Department parent) {
		this.code = code;
		this.name = name;
		this.parent = parent;
	}
}
