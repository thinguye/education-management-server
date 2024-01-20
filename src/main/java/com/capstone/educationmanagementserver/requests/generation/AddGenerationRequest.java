package com.capstone.educationmanagementserver.requests.generation;

import com.capstone.educationmanagementserver.models.Year;

import lombok.Data;

@Data
public class AddGenerationRequest {
	String code;
	String name;
	Year year;
}
