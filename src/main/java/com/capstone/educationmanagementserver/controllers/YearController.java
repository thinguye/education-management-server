package com.capstone.educationmanagementserver.controllers;

import javax.annotation.security.RolesAllowed;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.year.AddYearRequest;
import com.capstone.educationmanagementserver.requests.year.UpdateYearRequest;
import com.capstone.educationmanagementserver.services.interfaces.IYearService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/year")
@SuppressWarnings("rawtypes")
public class YearController {
	@Autowired
	private IYearService iYearService;
	
	@PostMapping(value = "/create")
	public Response create(@RequestBody AddYearRequest request) {
		try {
			iYearService.addYear(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Response getAll() {
		return Response.ok().setPayload(iYearService.getAllYear());
	}

	@GetMapping("/getYearById")
	public Response getYearById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iYearService.getYearById(id));
	}

	@GetMapping("/getYearByCode")
	public Response getYearByCode(@RequestParam(value = "code", required = true) String code) {
		return Response.ok().setPayload(iYearService.getYearByCode(code));
	}

	@PostMapping(value = "/remove")
	public Response removeYear(@RequestParam (value = "id", required = true) String id){
		try {
			iYearService.removeYear(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateYearRequest request) {
		try {
			iYearService.updateYear(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
