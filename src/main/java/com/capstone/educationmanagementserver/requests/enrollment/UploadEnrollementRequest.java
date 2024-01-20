package com.capstone.educationmanagementserver.requests.enrollment;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadEnrollementRequest {
	String id;
	MultipartFile file;
}
