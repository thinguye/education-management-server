package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("generation")
public class Generation {
	@Id
	String id;
	String code;
	String name;
	@DBRef
	Year year;

	@Builder
	public Generation(String code, String name, Year year) {
		this.code = code;
		this.name = name;
		this.year = year;
	}
}
