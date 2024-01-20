package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.curriculum.AddCurriculumRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddElectivesRequest;
import com.capstone.educationmanagementserver.requests.curriculum.AddSubjectToCurriculum;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateCirriculumRequest;
import com.capstone.educationmanagementserver.services.interfaces.ICurriculumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iCurriculumService.getAllCirriculums());
	}

	@GetMapping("/getCurriculumById")
	public Response getCurriculumById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iCurriculumService.getCirriculumById(id));
	}

	@GetMapping("/getSubjectByCurriculum")
	public Response getSubjectByCurriculum(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iCurriculumService.getSubjectByCurriculum(id));
	}
	
	@GetMapping("/getElectiveByCurriculum")
	public Response getElectiveByCurriculum(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iCurriculumService.getElectiveByCurriculum(id));
	}
	@PostMapping("/addElectiveToCurriculum")
	public Response addElectiveToCurriculum(@RequestBody AddElectivesRequest request) {
		try {
			iCurriculumService.addElectiveSubject(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	
	@PostMapping("/addSubjectToCurriculum")
	public Response addSubjectToCurriculum(@RequestBody AddSubjectToCurriculum request) {
		try {
			iCurriculumService.addSubjectToCurriculum(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
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

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateCirriculumRequest request) {
		try {
			iCurriculumService.updatingCurriculum(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
