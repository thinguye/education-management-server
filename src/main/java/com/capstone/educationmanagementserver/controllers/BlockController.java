package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Curriculum;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.repositories.interfaces.ICurriculumRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectRepository;
import com.capstone.educationmanagementserver.requests.block.AddBlock;
import com.capstone.educationmanagementserver.requests.curriculum.UpdateBlockRequest;
import com.capstone.educationmanagementserver.services.interfaces.IBlockService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectService;

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
	private ICurriculumRepository iCurriculumRepository;
	@Autowired
	private ISubjectRepository iSubjectRepository;
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
	public Response create(@RequestBody UpdateBlockRequest request) {
		try {
			iBlockService.updatingBlock(request.getId(),request.getSubjects());
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	@GetMapping(value="/findBySubject")
	public Response findBySubject(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "curriculum", required = true) String curriculum) {
		try {
			Curriculum c = iCurriculumRepository.findById(curriculum);
			Subject s = iSubjectRepository.findById(id);
			Block b = iBlockService.getBlockBySubjectAndCurriculum(s,c);
			return Response.ok().setPayload(b);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

}
