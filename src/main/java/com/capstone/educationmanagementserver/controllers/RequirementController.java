package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.block.AddBlock;
import com.capstone.educationmanagementserver.requests.requirement.CreateRequirementRequest;
import com.capstone.educationmanagementserver.services.interfaces.IRequirementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/requirement")
@SuppressWarnings("rawtypes")
public class RequirementController {
	@Autowired
	private IRequirementService iRequirementService;
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response uploadFile(@ModelAttribute MultipartFile file) {
		try {
			iRequirementService.createRequirementFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}
	@PostMapping(value = "/create")
	public Response create(@RequestBody CreateRequirementRequest request) {
		try {
			iRequirementService.createRequirement(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}
	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iRequirementService.getAllRequirement());
	}
	
}
