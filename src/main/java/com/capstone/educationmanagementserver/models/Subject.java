package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("subject")
public class Subject {
	@Id
	String id;
	@Indexed(unique = true)
	String code;
	String name;
	Integer theoryCredit;
	Integer labCredit;
	List<Subject> prerequisites = new ArrayList<>();
	List<Subject> previousCourses = new ArrayList<>();

	@Builder
	public Subject(String code, String name, Integer theoryCredit, Integer labCredit, List<Subject> prerequisites,
			List<Subject> previousCourses) {
		this.code = code;
		this.name = name;
		this.theoryCredit = theoryCredit;
		this.labCredit = labCredit;
		if (!prerequisites.isEmpty()) {
			this.prerequisites = prerequisites;
		}
		if (!previousCourses.isEmpty()) {
			this.previousCourses = previousCourses;
		}
	}
}
