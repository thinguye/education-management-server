package com.capstone.educationmanagementserver.requests.quarter;

import java.util.Date;

import com.capstone.educationmanagementserver.models.Year;

import lombok.Data;

@Data
public class AddQuarterRequest {
	String name;
	Year year;
	Date start;
	Date end;
}
