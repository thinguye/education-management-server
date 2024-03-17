package com.capstone.educationmanagementserver.models;

import java.util.List;

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
	Major organization;
	Integer credit;
	List<Generation> generation;
	List<Requirement> conditions;
	String pathImage;

	@Builder
	public Curriculum(String code, String name, List<Generation> generation, Major organization, Integer credit, List<Requirement> conditions) {
		this.code = code;
		this.name = name;
		this.generation = generation;
		this.organization = organization;
		this.credit = credit;
		this.conditions = conditions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Curriculum) {
			Curriculum new_name = (Curriculum) obj;
			if (this.id.equals(new_name.id)) {
				return true;
			}
		} 
		return false;
	}
}
