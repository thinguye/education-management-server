package com.capstone.educationmanagementserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("electiveGroup")
public class ElectiveGroup {
	@Id
	String id;
	String name;
	Integer credit;

	@Builder
	public ElectiveGroup(String name, Integer credit) {
		this.name = name;
		this.credit = credit;
	}

}
