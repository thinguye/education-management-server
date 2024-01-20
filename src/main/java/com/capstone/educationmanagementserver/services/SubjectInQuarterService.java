package com.capstone.educationmanagementserver.services;

import java.util.List;

import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.repositories.interfaces.ISubjectInQuarterRepository;
import com.capstone.educationmanagementserver.requests.quarter.AddSubjectToQuarter;
import com.capstone.educationmanagementserver.services.interfaces.IQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectInQuarterService;
import com.capstone.educationmanagementserver.services.interfaces.ISubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectInQuarterService implements ISubjectInQuarterService {
	@Autowired
	private ISubjectInQuarterRepository iSubjectInQuarterRepository;
	@Autowired
	private ISubjectService iSubjectService;
	@Autowired
	private IQuarterService iQuarterService;

	@Override
	public void addSubject(AddSubjectToQuarter request) {
		SubjectInQuarter subjectInQuarter = SubjectInQuarter.builder().subject(request.getSubject())
				.quarter(request.getQuarter()).lecturer(request.getLecturer()).build();
		iSubjectInQuarterRepository.save(subjectInQuarter);
	}

	@Override
	public List<SubjectInQuarter> getAll() {
		return iSubjectInQuarterRepository.findAll();
	}

	@Override
	public SubjectInQuarter getBySubjectQuarter(String subject, String quarter, String year) {
		Subject s = iSubjectService.getSubjectByCode(subject);
		Quarter q = iQuarterService.getQuarterByQuarterYear(quarter, year);
		return iSubjectInQuarterRepository.findBySubjectQuarter(s, q);
	}

	@Override
	public void removeSubject(String id) {
		SubjectInQuarter s = iSubjectInQuarterRepository.findById(id);
		iSubjectInQuarterRepository.remove(s);
		
	}

	@Override
	public SubjectInQuarter getSubjectById(String id) {
		return iSubjectInQuarterRepository.findById(id);
	}

}
