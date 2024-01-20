package com.capstone.educationmanagementserver.requests.curriculum;

import java.util.List;
import java.util.Map;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;
@Data
public class UpdateBlockRequest {
	String id;
	Map<Subject, Mandatory> subjects;
}
