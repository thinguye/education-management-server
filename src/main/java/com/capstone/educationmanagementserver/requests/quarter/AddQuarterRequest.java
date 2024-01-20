package com.capstone.educationmanagementserver.requests.quarter;

import com.capstone.educationmanagementserver.models.Year;

import lombok.Data;

@Data
public class AddQuarterRequest {
	String name;
	Year year;
}
