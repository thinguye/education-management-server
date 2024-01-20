package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.quarter.AddQuarterRequest;
import com.capstone.educationmanagementserver.requests.quarter.AddSubjectToQuarter;
import com.capstone.educationmanagementserver.requests.quarter.UpdateQuarterRequest;
import com.capstone.educationmanagementserver.services.interfaces.IQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInQuarterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quarter")
@SuppressWarnings("rawtypes")
public class QuarterController {
	@Autowired
	private IQuarterService iQuarterService;
	
	@Autowired
	private ISubjectInQuarterService iSubjectInQuarterService;

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddQuarterRequest request) {
		try {
			iQuarterService.addQuarter(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iQuarterService.getAllQuarter());
	}

	@GetMapping("/getQuarterById")
	public Response getQuarterById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iQuarterService.getQuarterById(id));
	}

	@GetMapping("/getQuartersByYear")
	public Response getQuarterByCode(@RequestParam(value = "year", required = true) String year) {
		return Response.ok().setPayload(iQuarterService.getQuartersByYear(year));
	}

	@PostMapping(value = "/remove")
	public Response removeQuarter(@RequestParam(value = "id", required = true) String id) {
		try {
			iQuarterService.removeQuarter(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response update(@RequestBody UpdateQuarterRequest request) {
		try {
			iQuarterService.updateQuarter(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@PostMapping(value="/addSubject")
	public Response addSubjects(@RequestBody AddSubjectToQuarter request) {
		try {
			iSubjectInQuarterService.addSubject(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@GetMapping("/getAllSubject")
	public Response getAllSubject() {
		return Response.ok().setPayload(iSubjectInQuarterService.getAll());
	}
	
	@PostMapping(value = "/removeSubject")
	public Response removeSubject(@RequestParam(value = "id", required = true) String id) {
		try {
			iSubjectInQuarterService.removeSubject(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	@GetMapping("/getSubjectById")
	public Response getSubjectById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iSubjectInQuarterService.getSubjectById(id));
	}
}
