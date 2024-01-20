package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("electiveSubject")
public class ElectiveSubject {
	@Id
	String id;
	@DBRef
	Subject subject;
	@DBRef
	ElectiveGroup electiveGroup;

	@Builder
	public ElectiveSubject(Subject subject, ElectiveGroup electiveGroup) {
		this.electiveGroup = electiveGroup;
		this.subject = subject;
	}
}
