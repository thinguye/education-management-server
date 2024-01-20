package com.capstone.educationmanagementserver.models;

import com.capstone.educationmanagementserver.enums.Division;
import com.capstone.educationmanagementserver.enums.Gender;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Lecturer extends HumanResource {
	@DBRef
	Organization organization;

	@Builder
	public Lecturer(String code, String lastName, String middleName, String firstName, String email, Gender gender,
			Organization department) {
		super(code, lastName, middleName, firstName, gender, email, Division.LECTURER);
		this.organization = department;
	}
}
