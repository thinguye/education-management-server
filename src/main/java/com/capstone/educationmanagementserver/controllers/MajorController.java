package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.major.AddMajorRequest;
import com.capstone.educationmanagementserver.services.interfaces.IMajorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/major")
@SuppressWarnings("rawtypes")
public class MajorController {
	@Autowired
	private IMajorService iMajorService;
	
	@PostMapping(value = "/create")
	public Response create(@RequestBody AddMajorRequest request) {
		try {
			iMajorService.createMajor(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}
	
//	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public Response addStudentsFromFile(@ModelAttribute MultipartFile file) {
//		try {
//			iMajorService.addMajorFromFile(file);
//			return Response.ok().setStatus(Status.OK);
//		} catch (Exception e) {
//			return Response.ok().setErrors(e);
//		}
//	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iMajorService.getAllMajor());
	}

	@GetMapping("/getById")
	public Response getMajorById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iMajorService.getMajorById(id));
	}

	@PostMapping(value = "/remove")
	public Response removeMajor(@RequestParam(value = "id", required = true) String id) {
		try {
			iMajorService.removeMajor(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
}
