package com.capstone.educationmanagementserver.models;

import java.util.ArrayList;
import java.util.List;

import com.capstone.educationmanagementserver.requests.curriculum.UpdateCirriculumRequest;

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
	@DBRef
	List<Subject> subjects;
	@DBRef
	List<ElectiveSubject> electiveSubjects;

	@Builder
	public Curriculum(String code, String name, Generation generation, Organization organization) {
		this.code = code;
		this.name = name;
		this.generation = generation;
		this.organization = organization;
		this.subjects = new ArrayList<>();
		this.electiveSubjects = new ArrayList<>();
		this.theoryCredit = 0;
		this.labCredit = 0;
	}

	public void updateCurriculum(UpdateCirriculumRequest request) {
		this.code = request.getCode();
		this.name = request.getName();
		this.generation = request.getGeneration();
		this.organization = request.getOrganization();
		this.theoryCredit = request.getCredit();
	}
}
