package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("quarter")
public class Quarter {
	@Id
	String id;
	String name;
	@DBRef
	Year year;

	@Builder
	public Quarter(String name, Year year) {
		this.name = name;
		this.year = year;
	}
}
