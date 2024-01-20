package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.department.AddDepartmentRequest;
import com.capstone.educationmanagementserver.requests.department.UpdateDepartmentRequest;
import com.capstone.educationmanagementserver.services.interfaces.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/organization")
@SuppressWarnings("rawtypes")
public class OrganizationController {
	@Autowired
	private IDepartmentService iDepartmentService;

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddDepartmentRequest request) {
		try {
			iDepartmentService.createDepartment(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response update(@RequestBody UpdateDepartmentRequest request) {
		try {
			iDepartmentService.updateDepartment(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/deleteById")
	public Response deleteById(@RequestParam(value = "id") String id) {
		try {
			iDepartmentService.deleteById(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@GetMapping("/getAll")
	public Response getAllDepartments() {
		try {
			return Response.ok().setPayload(iDepartmentService.getAllDepartments());
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@GetMapping("/getById")
	public Response getDepartmentById(@RequestParam(value = "id") String id) {
		try {
			return Response.ok().setPayload(iDepartmentService.getDepartmentById(id));
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@GetMapping("/getBySchool")
	public Response getDepartmentsBySchool(@RequestParam(value = "school") String school) {
		try {
			return Response.ok().setPayload(iDepartmentService.getDepartmentsBySchool(school));
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
