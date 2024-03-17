package com.capstone.educationmanagementserver.requests.block;

import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;

import lombok.Data;

@Data
public class AddBlock {
	String code;
	String name;
	Block paraBlock;
	Curriculum curriculum;
	Integer credit;
}
