package com.capstone.educationmanagementserver.requests.enrollment;

import com.capstone.educationmanagementserver.models.SubjectInQuarter;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadGradeRequest {
	String subject;
	MultipartFile file;
}
