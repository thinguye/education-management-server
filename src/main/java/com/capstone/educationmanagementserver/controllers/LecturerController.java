package com.capstone.educationmanagementserver.controllers;

import java.util.HashSet;
import java.util.Set;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.auth.SignupRequest;
import com.capstone.educationmanagementserver.requests.staff.AddLecturerRequest;
import com.capstone.educationmanagementserver.requests.staff.UpdateLecturerRequest;
import com.capstone.educationmanagementserver.services.interfaces.ILecturerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/lecturer")
@SuppressWarnings("rawtypes")
public class LecturerController {
	@Autowired
	private ILecturerService iLecturerService;
	@Autowired
	private AuthController authController;
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addStudentsFromFile(@ModelAttribute MultipartFile file) {
		try {
			iLecturerService.addLecturersFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	@PostMapping(value = "/create")
	public Response create(@RequestBody AddLecturerRequest request) {
		try {
			iLecturerService.addLecturer(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iLecturerService.getAllLecturer());
	}

	@GetMapping("/getLecturerById")
	public Response getLecturerById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iLecturerService.getLecturerById(id));
	}

	@GetMapping("/getLecturerByCode")
	public Response getLecturerByCode(@RequestParam(value = "code", required = true) String code) {
		return Response.ok().setPayload(iLecturerService.getLecturerByCode(code));
	}
	
	@GetMapping("/getLecturerByEmail")
	public Response getLecturerByEmail(@RequestParam(value = "email", required = true) String email) {
		return Response.ok().setPayload(iLecturerService.getLecturerByEmail(email));
	}

	@GetMapping("/getLecturerByName")
	public Response getLecturerByName(@RequestParam(value = "name", required = true) String name) {
		return Response.ok().setPayload(iLecturerService.getLecturerByName(name));
	}

	@PostMapping(value = "/remove")
	public Response removeLecturer(@RequestParam(value = "id", required = true) String id) {
		try {
			iLecturerService.removeLecturer(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateLecturerRequest request) {
		try {
			iLecturerService.updateLecturer(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	


}
