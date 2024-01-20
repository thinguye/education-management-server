package com.capstone.educationmanagementserver.controllers;

import java.util.HashSet;
import java.util.Set;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.auth.SignupRequest;
import com.capstone.educationmanagementserver.requests.student.AddStudentRequest;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;
import com.capstone.educationmanagementserver.services.interfaces.IStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/student")
@SuppressWarnings("rawtypes")
public class StudentController {
	@Autowired
	private IStudentService iStudentService;
	@Autowired
	AuthController authController;

	
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addStudentsFromFile(@ModelAttribute MultipartFile file) {
		try {
			iStudentService.addStudentFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddStudentRequest request) {
		try {
			iStudentService.addStudent(request);
			Set<String> roles = new HashSet<>();
			roles.add("student");
			String password = String.valueOf(request.getDateOfBirth().getDate())
					.concat(String.valueOf(request.getDateOfBirth().getMonth()))
					.concat(String.valueOf(request.getDateOfBirth().getYear()));
			authController.registerUser(new SignupRequest(request.getFirstName(), request.getLastName(),
					request.getEmail(), roles, password));
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	
	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iStudentService.getAllStudent());
	}

	@GetMapping("/getStudentById")
	public Response getStudentById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iStudentService.getStudentById(id));
	}

	@GetMapping("/getStudentByCode")
	public Response getStudentByCode(@RequestParam(value = "code", required = true) String code) {
		return Response.ok().setPayload(iStudentService.getStudentByCode(code));
	}

	@PostMapping(value = "/remove")
	public Response removeStudent(@RequestParam(value = "id", required = true) String id) {
		try {
			iStudentService.removeStudent(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateStudentProfileRequest request) {
		try {
			iStudentService.updateStudentProfile(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

}
