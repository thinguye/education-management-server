package com.capstone.educationmanagementserver.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("year")
public class Year {
	@Id
	String id;
	String code;
	String name;
	Date startYear;
	Date endYear;

	@Builder
	public Year(String code, String name, Date startYear, Date endYear) {
		this.code = code;
		this.name = name;
		this.startYear = startYear;
		this.endYear = endYear;
	}
}
