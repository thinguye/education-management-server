package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.lang.Nullable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("subjectInQuarter")
public class SubjectInQuarter {
	@Id
	String id;
	Subject subject;
	Quarter quarter;
	@Nullable
	Lecturer lecturer;
	Integer maxStudents;
	Integer numberOfStudents;
	List<TimeTable> timeTable = new ArrayList<>();

	@Builder
	public SubjectInQuarter(Subject subject, Quarter quarter, Lecturer lecturer, Integer maxStudents, List<TimeTable> timeTable) {
		this.subject = subject;
		this.quarter = quarter;
		if (lecturer != null) {
			this.lecturer = lecturer;
		}
		this.maxStudents = maxStudents;
		this.numberOfStudents = 0;
		this.timeTable = timeTable;
	}
}