package com.capstone.educationmanagementserver.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.capstone.educationmanagementserver.enums.ERole;
import com.capstone.educationmanagementserver.general.JwtResponse;
import com.capstone.educationmanagementserver.general.MessageResponse;
import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.models.Role;
import com.capstone.educationmanagementserver.models.User;
import com.capstone.educationmanagementserver.repositories.interfaces.IRoleRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IUserRepository;
import com.capstone.educationmanagementserver.requests.auth.LoginRequest;
import com.capstone.educationmanagementserver.requests.auth.SignupRequest;
import com.capstone.educationmanagementserver.security.jwt.JwtUtils;
import com.capstone.educationmanagementserver.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@SuppressWarnings("rawtypes")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserRepository iUserRepository;

	@Autowired
	IRoleRepository iRoleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getUsername().contains("@")
				? loginRequest.getUsername().substring(0, loginRequest.getUsername().indexOf("@"))
				: loginRequest.getUsername();
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		JwtResponse temp = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
				roles);
		return ResponseEntity.ok(temp);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		String username = signUpRequest.getEmail().substring(0, signUpRequest.getEmail().indexOf("@"));
		if (iUserRepository.existsByUsername(username)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already in use!"));
		}

		if (iUserRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstname(), signUpRequest.getLastname(), username,
				signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = iRoleRepository.findByName(ERole.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
					case "Admin":
					case "ADMIN":
						Role adminRole = iRoleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "staff":
					case "Staff":
					case "STAFF":
						Role modRole = iRoleRepository.findByName(ERole.ROLE_STAFF)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
						break;
					case "lecturer":
					case "Lecturer":
					case "LECTURER":
						Role lecturerRole = iRoleRepository.findByName(ERole.ROLE_LECTURER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(lecturerRole);
						break;
					default:
						Role userRole = iRoleRepository.findByName(ERole.ROLE_STUDENT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		iUserRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/addRole")
	public Response addRole() {
		try {
			List<Role> roles = new ArrayList<>();
			roles.add(new Role(ERole.ROLE_ADMIN));
			roles.add(new Role(ERole.ROLE_STAFF));
			roles.add(new Role(ERole.ROLE_STUDENT));
			roles.add(new Role(ERole.ROLE_LECTURER));
			iRoleRepository.insert(roles);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
