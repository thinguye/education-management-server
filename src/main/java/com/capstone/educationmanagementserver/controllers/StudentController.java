package com.capstone.educationmanagementserver.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capstone.educationmanagementserver.general.Response;
import com.capstone.educationmanagementserver.general.Response.Status;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.SubjectInBlock;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.requests.auth.SignupRequest;
import com.capstone.educationmanagementserver.requests.response.Undergraduate;
import com.capstone.educationmanagementserver.requests.student.AddStudentRequest;
import com.capstone.educationmanagementserver.requests.student.UpdateStudentProfileRequest;
import com.capstone.educationmanagementserver.services.interfaces.IStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/student")
@SuppressWarnings("rawtypes")
public class StudentController {
	@Autowired
	private IStudentService iStudentService;
	@Autowired
	AuthController authController;

	@PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response addStudentsFromFile(@ModelAttribute MultipartFile file) {
		try {
			iStudentService.addStudentFromFile(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/create")
	public Response create(@RequestBody AddStudentRequest request) {
		try {
			iStudentService.addStudent(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@PostMapping(value = "/updateStatusFromFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response updateStatusFromFile(MultipartFile file) {
		try {

			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping(value = "/getStudentGraduated")
	public Response getStudentGraduated() {
		return Response.ok().setPayload(iStudentService.getStudentGraduated());
	}

	@GetMapping(value = "/getStudentUndergraduated")
	public Response getStudentUndergraduated() {
		Undergraduate t = iStudentService.getStudentUndergraduated();
		return Response.ok().setPayload(t);
	}

	@GetMapping(value = "/getListStudentUndergraduated")
	public Response getListStudentUndergraduated() {
		return Response.ok().setPayload(iStudentService.getListStudentUnderGraduate());
	}

	@GetMapping("/getAll")
	public Response getAll() {

		return Response.ok().setPayload(iStudentService.getAllStudent());
	}

	@GetMapping("/getStudentById")
	public Response getStudentById(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iStudentService.getStudentById(id));
	}

	@GetMapping("/getStudentByCode")
	public Response getStudentByCode(@RequestParam(value = "code", required = true) String code) {
		return Response.ok().setPayload(iStudentService.getStudentByCode(code));
	}

	@PostMapping(value = "/remove")
	public Response removeStudent(@RequestParam(value = "id", required = true) String id) {
		try {
			iStudentService.removeStudent(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/update")
	public Response create(@RequestBody UpdateStudentProfileRequest request) {
		try {
			iStudentService.updateStudentProfile(request);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@GetMapping(value = "/getIncompleteSubjects")
	public Response getIncompleteSubjects(@RequestParam(value = "id", required = true) String id) {
		List<SubjectInQuarter> list = iStudentService.getIncompleteSubjects(id);
		return Response.ok().setPayload(list);
	}

	@GetMapping(value = "/getSubjectsCanLearn")
	public Response getSubjectsCanLearn(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iStudentService.getSubjectsCanLearn(id));
	}

	@PostMapping(value = "/setAsLeaved")
	public Response setAsLeaved(@RequestParam(value = "id", required = true) String id) {
		try {
			iStudentService.setAsLeaved(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@GetMapping(value = "/getIncompleteStudent")
	public Response getIncompleteStudent() {
		return Response.ok().setPayload(iStudentService.getStudentsByStatus("INCOMPLETE"));

	}

	@GetMapping(value = "/getLeavedStudent")
	public Response getLeavedStudent() {
		return Response.ok().setPayload(iStudentService.getStudentsByStatus("Leaved"));
	}

	@GetMapping(value = "/getGraduatedStudent")
	public Response getGradutatedStudent() {
		return Response.ok().setPayload(iStudentService.getStudentsByStatus("Graduated"));
	}

	@GetMapping(value = "/getCompletedStudent")
	public Response getCompletedStudent() {
		return Response.ok().setPayload(iStudentService.getStudentsByStatus("Completed"));
	}

	@GetMapping(value = "/getByEmail")
	public Response getByEmail(@RequestParam(value = "email", required = true) String email) {
		return Response.ok().setPayload(iStudentService.getStudentByEmail(email));
	}

	@PostMapping(value = "/setAsGraduated")
	public Response setAsGraduated(@RequestParam(value = "id", required = true) String id) {
		try {
			iStudentService.setAsGraduated(id);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.ok().setErrors(e);
		}
	}

	@PostMapping(value = "/importCertificate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Response importCertificate(MultipartFile file) {
		try {
			iStudentService.importCertificate(file);
			return Response.ok().setStatus(Status.OK);
		} catch (Exception e) {
			return Response.exception();
		}
	}

	@GetMapping(value = "/getStatusByFilter")
	public Response getStatusByFilter(@RequestParam(value = "generation", required = false) String generation,
			@RequestParam(value = "department", required = false) String department) {
		return Response.ok().setPayload(iStudentService.getStatusByFilter(generation, department));
	}

	@GetMapping(value = "/getCertificateByFilter")
	public Response getCertificateByFilter(@RequestParam(value = "generation", required = false) String generation,
			@RequestParam(value = "department", required = false) String department,
			@RequestParam(value = "status", required = false) String status) {
		return Response.ok().setPayload(iStudentService.getCertificateByFilter(generation, department, status));
	}

	@GetMapping(value = "/getTotalStudent")
	public Response getTotalStudent() {
		return Response.ok().setPayload(iStudentService.getTotalStudent());
	}

	@GetMapping(value = "/getTimeTable")
	public Response getTimeTable(@RequestParam(value = "quarter", required = false) String quarter,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "student", required = false) String student) {
		return Response.ok().setPayload(iStudentService.getTimeTable(quarter, year, student));
	}
	
	@GetMapping(value = "/getCredits")
	public Response getCrdeit(@RequestParam(value = "id", required = true) String id) {
		return Response.ok().setPayload(iStudentService.getCredits(id));
	}

}
