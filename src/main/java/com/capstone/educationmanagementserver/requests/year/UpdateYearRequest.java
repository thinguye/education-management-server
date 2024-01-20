package com.capstone.educationmanagementserver.requests.year;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateYearRequest {
	String id;
	String code;
	String name;
	Date startYear;
	Date endYear;
}
