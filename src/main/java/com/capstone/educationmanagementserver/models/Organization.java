package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("organization")
public class Organization {
	@Id
	String id;
	String code;
	String name;
	@DBRef
	Organization parent;

	@Builder
	public Organization(String code, String name, Organization parent) {
		this.code = code;
		this.name = name;
		this.parent = parent;
	}
}
