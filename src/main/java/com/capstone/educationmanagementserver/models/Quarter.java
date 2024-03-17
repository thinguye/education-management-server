package com.capstone.educationmanagementserver.models;

import java.util.Date;

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
	Date start;
	Date end;
	Year year;

	@Builder
	public Quarter(String name, Year year, Date start, Date end) {
		this.name = name;
		this.year = year;
		this.start=start;
		this.end=end;
	}
}
