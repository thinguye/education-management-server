package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.block.AddBlock;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateCirriculumRequest;
import com.capstone.educationmanagementserver.services.interfaces.IBlockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/block")
@SuppressWarnings("rawtypes")
public class BlockController {
	@Autowired
	private IBlockService iBlockService;

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddBlock request) {
		try {
			iBlockService.addBlock(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping("/getAll")
	public Response getAll() {
		return Response.ok().setPayload(iBlockService.getAllBlocks());
	}

	@GetMapping("/getBlockById")
	public Response getBlockById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iBlockService.getBlockById(id));
	}

	@GetMapping("/getBlocksByCurriculum")
	public Response getBlocksByCurriculum(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iBlockService.getBlocksByCurriculum(id));
	}

	@PostMapping(value = "/remove")
	public Response removeBlock(@RequestParam(value = "id", required = true) String id) {
		try {
			iBlockService.removeBlock(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateCirriculumRequest request) {
		try {
			iBlockService.updatingBlock(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	@PostMapping(value="/addSubjects")
	public Response addSubjects(@RequestBody UpdateCirriculumRequest request) {
		try {
			iBlockService.updatingBlock(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
}
