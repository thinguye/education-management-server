package com.capstone.educationmanagementserver.requests.enrollment;

import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;

import lombok.Data;

@Data
public class AddEnrollmentRequest {
	String student;
	String subject;
}
