package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.requests.enrollment.UploadEnrollementRequest;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInQuarterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/subjectInQuarter")
@SuppressWarnings("rawtypes")
public class SubjectInQuarterController {
	@Autowired
	private ISubjectInQuarterService iSubjectInQuarterService;
	
	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addSubjectsFromFile(@ModelAttribute MultipartFile file) {
		try {
			iSubjectInQuarterService.addSubjectsFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}
	
	@GetMapping(value="/getByLecturer")
	public Response getByLecturer(@RequestParam(value="id",required = true) String id) {
		return Response.ok().setPayload(iSubjectInQuarterService.getByLecturer(id));
	}
	@GetMapping(value="/getTeachingSubjects")
	public Response getTaughtSubjects(@RequestParam(value="id", required = true) String id) {
		return Response.ok().setPayload(iSubjectInQuarterService.getTeachingSubjects(id));
	}
	@GetMapping(value="/getNoStudents")
	public Response getNoStudents(@RequestParam(value="id",required = true) String id, @RequestParam(value="code",required = false) String code, @RequestParam(value="generation",required = false) String generation) {
		return Response.ok().setPayload(iSubjectInQuarterService.getNoStudents(id,code,generation));
	}
	@GetMapping(value="/getDistinctSubjectsByLecturer")
	public Response getDistinctSubjects(@RequestParam(value="id",required = true) String id) {
		return Response.ok().setPayload(iSubjectInQuarterService.getDistinctSubjects(id));
	}
}
