package com.capstone.educationmanagementserver.requests.subjectInBlock;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;

import lombok.Data;

@Data
public class UpdateSubjectInBlock {
	String id;
	Block block;
	Mandatory mandatory;
}
