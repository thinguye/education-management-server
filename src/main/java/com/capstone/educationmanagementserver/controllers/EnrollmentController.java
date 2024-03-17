package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.enrollment.AddEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UpdateEnrollmentRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;
import com.capstone.educationmanagementserver.requests.enrollment.UploadGradeRequest;
import com.capstone.educationmanagementserver.services.interfaces.IEnrollmentService;

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
@RequestMapping("api/enrollment")
@SuppressWarnings("rawtypes")
public class EnrollmentController {
	@Autowired
	private IEnrollmentService iEnrollmentService;

	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addEnrollmentsFromFile(@ModelAttribute UploadEnrollementRequest request) {
		try {
			iEnrollmentService.addEnrollmentFromFile(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	@PostMapping(value = "/create")
	public Response create(@RequestBody AddEnrollmentRequest request) {
		try {
			iEnrollmentService.addEnrollment(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iEnrollmentService.getAllEnrollment());
	}
	
	@GetMapping("/getStudentBySubject")
	public Response getStudentBySubject(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iEnrollmentService.getStudentBySubject(id));
	}
	
	@GetMapping("/getSubjectByStudent")
	public Response getSubjectByStudent(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iEnrollmentService.getSubjectByStudent(id));
	}

	@GetMapping("/getEnrollmentById")
	public Response getEnrollmentById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iEnrollmentService.getEnrollmentById(id));
	}

	@PostMapping(value = "/remove")
	public Response removeEnrollment(@RequestParam(value = "id", required = true) String id) {
		try {
			iEnrollmentService.removeEnrollment(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response update(@RequestBody UpdateEnrollmentRequest request) {
		try {
			iEnrollmentService.updateEnrollment(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	@PostMapping(value = "/updateGradeFile")
	public Response updateFile(@ModelAttribute UploadGradeRequest request) {
		try {
			iEnrollmentService.updateGradeFromFile(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
