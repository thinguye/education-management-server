package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.services.interfaces.IResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/result")
@SuppressWarnings("rawtypes")
public class ResultController {
	@Autowired
	private IResultService iResultService;
	@GetMapping("/getResultByStudent")
	public Response getResultByStudent(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iResultService.getResultByStudent(id));
	}
	@GetMapping("/getSubjectInLastQuarterByStudent")
	public Response getSubjectInLastQuarterByStudent(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iResultService.getSubjectInLastQuarterByStudent(id));
	}
}
