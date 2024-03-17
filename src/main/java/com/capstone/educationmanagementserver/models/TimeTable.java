package com.capstone.educationmanagementserver.models;

import java.util.Date;

import lombok.Data;

@Data
public class TimeTable {
	String time;
	String day;
	Integer lesson;
	Date from;
	Date to;

	public TimeTable(String time, String day, Integer lesson, Date from, Date to) {
		this.time = time;
		this.day = day;
		this.lesson = lesson;
		this.from = from;
		this.to = to;
	}
}
