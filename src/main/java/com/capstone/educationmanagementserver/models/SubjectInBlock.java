package com.capstone.educationmanagementserver.models;

import com.capstone.educationmanagementserver.enums.Mandatory;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Builder;
import lombok.Data;

@Data
@Document("subjectInBlock")
public class SubjectInBlock {
	@Id
	String id;
	String code;
	Block block;
	Subject subject;
	Mandatory mandatory;
	

	@Builder
	public SubjectInBlock(Subject subject, Mandatory mandatory, Block block) {
		this.code = subject.getCode();
		this.subject = subject;
		this.block = block;
		this.mandatory = mandatory;
	}
}
