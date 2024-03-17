package com.capstone.educationmanagementserver.requests.curriculum;

import java.util.List;

import com.capstone.educationmanagementserver.models.Generation;
import com.capstone.educationmanagementserver.models.Major;
import com.capstone.educationmanagementserver.models.Department;

import lombok.Data;

@Data
public class AddCurriculumRequest {
	String code;
	String name;
	Major department;
	List<Generation> generation;
	Integer credit;
}
