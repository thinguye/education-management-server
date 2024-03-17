package com.capstone.educationmanagementserver.requests.curriculum;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddProcessRequest {
	String id;
	MultipartFile file;
}
