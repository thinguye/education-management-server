package com.capstone.educationmanagementserver.requests.quarter;

import com.capstone.educationmanagementserver.models.Year;

import lombok.Data;

@Data
public class UpdateQuarterRequest {
	String id;
	String name;
	Year year;
}
