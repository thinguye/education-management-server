package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.subject.AddSubjectRequest;
import com.capstone.educationmanagementserver.requests.subject.UpdateRequirements;
import com.capstone.educationmanagementserver.requests.subject.UpdateSubjectRequest;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectService;

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
@RequestMapping("api/subject")
@SuppressWarnings("rawtypes")
public class SubjectController {
	@Autowired
	private ISubjectService iSubjectService;
	@PostMapping(value ="/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addStudentsFromFile(@ModelAttribute MultipartFile file) {
		try {
			iSubjectService.addSubjectFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@PostMapping(value = "/create")
	public Response create(@RequestBody AddSubjectRequest request) {
		try {
			iSubjectService.addSubject(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iSubjectService.getAllSubject());
	}

	@GetMapping("/getSubjectById")
	public Response getSubjectById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iSubjectService.getSubjectById(id));
	}

	@GetMapping("/getSubjectByCode")
	public Response getSubjectByCode(@RequestParam(value = "code", required = true) String code) {
		return Response.ok().setPayload(iSubjectService.getSubjectByCode(code));
	}

	@GetMapping("/getSubjectByName")
	public Response getSubjectByName(@RequestParam(value = "name", required = true) String name) {
		return Response.ok().setPayload(iSubjectService.getSubjectByName(name));
	}

	@PostMapping(value = "/remove")
	public Response removeSubject(@RequestParam(value = "id", required = true) String id) {
		try {
			iSubjectService.removeSubject(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response updateSubject(@RequestBody UpdateSubjectRequest request) {
		try {
			iSubjectService.updateSubject(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@PostMapping(value="/updatePrerequire")
	public Response updatePrerequire(@RequestBody UpdateRequirements request) {
		try {
			iSubjectService.updatePrerequire(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
