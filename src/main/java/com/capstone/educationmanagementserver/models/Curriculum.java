package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.List;

import com.capstone.educationmanagementserver.requests.curriculum.UpdateBlockRequest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document("curriculum")
public class Curriculum {
	@Id
	String id;
	String code;
	String name;
	@DBRef
	Generation generation;
	@DBRef
	Organization organization;
	Integer theoryCredit;
	Integer labCredit;

	@Builder
	public Curriculum(String code, String name, Generation generation, Organization organization) {
		this.code = code;
		this.name = name;
		this.generation = generation;
		this.organization = organization;
		this.theoryCredit = 0;
		this.labCredit = 0;
	}
}
