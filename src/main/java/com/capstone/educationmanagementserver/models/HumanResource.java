package com.capstone.educationmanagementserver.models;

import com.capstone.educationmanagementserver.enums.Division;
import com.capstone.educationmanagementserver.enums.Gender;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "humanResource")
public class HumanResource {
	@Id
	private String id;
	private String code;
	private String lastName;
	private String middleName;
	private String firstName;
	private Gender gender;
	private String email;
	private Division division;

	@Builder(builderMethodName = "builderHuman")
	public HumanResource(String code, String lastName, String middleName, String firstName, Gender gender, String email,
			Division division) {
		this.code = code;
		this.lastName = lastName;
		this.middleName = middleName;
		this.firstName = firstName;
		this.gender = gender;
		this.email = email;
		this.division = division;
	}
}
