package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.generation.AddGenerationRequest;
import com.capstone.educationmanagementserver.requests.generation.UpdateGenerationRequest;
import com.capstone.educationmanagementserver.services.interfaces.IGenerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/generation")
@SuppressWarnings("rawtypes")
public class GenerationController {
	@Autowired
	private IGenerationService iGenerationService;

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddGenerationRequest request) {
		try {
			iGenerationService.addGeneration(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iGenerationService.getAllGeneration());
	}

	@GetMapping("/getGenerationById")
	public Response getGenerationById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iGenerationService.getGenerationById(id));
	}

	@GetMapping("/getGenerationByCode")
	public Response getGenerationByCode(@RequestParam(value = "code", required = true) String code) {
		return Response.ok().setPayload(iGenerationService.getGenerationByCode(code));
	}

	@GetMapping("/getGenerationByName")
	public Response getGenerationByName(@RequestParam(value = "name", required = true) String name) {
		return Response.ok().setPayload(iGenerationService.getGenerationByName(name));
	}

	@PostMapping(value = "/remove")
	public Response removeGeneration(@RequestParam(value = "id", required = true) String id) {
		try {
			iGenerationService.removeGeneration(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateGenerationRequest request) {
		try {
			iGenerationService.updateGeneration(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
