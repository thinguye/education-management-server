package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInBlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/subjectInBlock")
@SuppressWarnings("rawtypes")
public class SubjectInBlockController {
	@Autowired
	private ISubjectInBlockService iSubjectInBlockService;
	
	@GetMapping("/getSubjectsInBlock")
	public Response getSubjectsInBlock(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iSubjectInBlockService.getBlocksByCurriculum(id));
	}
	@GetMapping("/getSubjectsInCurriculum")
	public Response getSubjectsInCurriculum(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iSubjectInBlockService.getSubjectsByCurriculum(id));
	}
	@GetMapping("/getAll")
	public Response getAll() {
		
		return Response.ok().setPayload(iSubjectInBlockService.getAll());
	}
}
