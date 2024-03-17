package com.capstone.educationmanagementserver.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capstone.educationmanagementserver.models.Enrollment;
import com.capstone.educationmanagementserver.models.Result;
import com.capstone.educationmanagementserver.models.Student;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.IResultRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.IStudentRepository;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInBlockRepository;
import com.capstone.educationmanagementserver.services.interfaces.IResultService;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServive implements IResultService {
	@Autowired
	private IResultRepository iResultRepository;
	@Autowired
	private IStudentRepository iStudentRepository;

	@Override
	public List<Result> getResultByStudent(String id) {
		Student st = iStudentRepository.findById(id);
		return iResultRepository.getResultByStudent(st);
	}
	
	@Override
	public List<SubjectInQuarter> getSubjectInLastQuarterByStudent(String id) {
		Student st = iStudentRepository.findById(id);
		Date date = new Date();
		List<SubjectInQuarter> res = new ArrayList<>();
		Result r = iResultRepository.getResultLastQuarterByStudent(st,date);
		if (r!=null) {
			return r.getEnrollments().stream().map(Enrollment::getSubject).toList();
		}
		return res;
	}

}
