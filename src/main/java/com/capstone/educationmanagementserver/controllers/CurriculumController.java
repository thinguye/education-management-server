package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping("api/curriculum")
@SuppressWarnings("rawtypes")
public class CurriculumController {
	@Autowired
	private ICurriculumService iCurriculumService;

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddCurriculumRequest request) {
		try {
			iCurriculumService.addCirriculum(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}
	
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addStudentsFromFile(@ModelAttribute MultipartFile file) {
		try {
			iCurriculumService.addCurriculumFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iCurriculumService.getAllCirriculums());
	}

	@GetMapping("/getCurriculumById")
	public Response getCurriculumById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iCurriculumService.getCirriculumById(id));
	}
	
	@GetMapping("/getCurriculumByDepartment")
	public Response getCurriculumByDepartment(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iCurriculumService.getCirriculumByDepartment(id));
	}

	@PostMapping(value = "/remove")
	public Response removeCurriculum(@RequestParam(value = "id", required = true) String id) {
		try {
			iCurriculumService.removeCirriculum(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
}
