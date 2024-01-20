package com.capstone.educationmanagementserver.requests.auth;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignupRequest {

	@NotBlank
	@Size(max = 50)
	private String firstname;

	@NotBlank
	@Size(max = 50)
	private String lastname;

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> roles;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public SignupRequest(String firstname, String lastname, String email, Set<String> roles, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.roles = roles;
		this.password = password;
	}

}
