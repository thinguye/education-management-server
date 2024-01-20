package com.capstone.educationmanagementserver.requests.subject;

import java.util.List;

import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;

@Data
public class UpdateRequirements {
	String id;
	List<Subject> subjects;
}
