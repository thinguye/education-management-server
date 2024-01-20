package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.elective.AddElectiveGroupRequest;
import com.capstone.educationmanagementserver.requests.elective.AddElectiveRequest;
import com.capstone.educationmanagementserver.requests.elective.UpdateElectiveGroupRequest;
import com.capstone.educationmanagementserver.requests.elective.UpdateElectiveRequest;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveGroupService;
import com.capstone.educationmanagementserver.services.interfaces.IElectiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/elective")
@SuppressWarnings("rawtypes")
public class ElectiveController {
	@Autowired
	private IElectiveService iElectiveService;
	@Autowired
	private IElectiveGroupService iElectiveGroupService;

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddElectiveRequest request) {
		try {
			iElectiveService.addElective(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iElectiveService.getAllElective());
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateElectiveRequest request) {
		try {
			iElectiveService.updateElective(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/remove")
	public Response removeElective(@RequestParam(value = "id", required = true) String id) {
		try {
			iElectiveService.removeElective(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@GetMapping("/getElectiveById")
	public Response getElectiveById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iElectiveService.getElectiveById(id));
	}

	@GetMapping("/getElectiveByGroup")
	public Response getElectiveByCode(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iElectiveService.getByGroup(id));
	}

	@GetMapping("/getElectiveByName")
	public Response getElectiveByName(@RequestParam(value = "name", required = true) String name) {
		return Response.ok().setPayload(iElectiveService.getElectiveByName(name));
	}
	
	
	@PostMapping(value = "/createGroup")
	public Response createGroup(@RequestBody AddElectiveGroupRequest request) {
		try {
			iElectiveGroupService.addElectiveGroup(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAllGroup")
	public Response getAllGroup() {
		return Response.ok().setPayload(iElectiveGroupService.getAllElectiveGroup());
	}

	@PostMapping(value = "/updateGroup")
	public Response create(@RequestBody UpdateElectiveGroupRequest request) {
		try {
			iElectiveGroupService.updateElectiveGroup(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/removeGroup")
	public Response removeElectiveGroup(@RequestParam(value = "id", required = true) String id) {
		try {
			iElectiveGroupService.removeElectiveGroup(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@GetMapping("/getElectiveGroupById")
	public Response getElectiveGroupById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iElectiveGroupService.getElectiveGroupById(id));
	}

}
