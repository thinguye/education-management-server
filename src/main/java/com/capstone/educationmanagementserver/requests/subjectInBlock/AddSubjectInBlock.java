package com.capstone.educationmanagementserver.requests.subjectInBlock;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;

@Data
public class AddSubjectInBlock {
	Subject subject;
	Block block;
	Mandatory mandatory;
}
