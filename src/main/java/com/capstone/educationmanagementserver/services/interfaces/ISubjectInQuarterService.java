package com.capstone.educationmanagementserver.services.interfaces;

import java.util.List;

import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.requests.quarter.AddSubjectToQuarter;

public interface ISubjectInQuarterService {

	void addSubject(AddSubjectToQuarter request);

	List<SubjectInQuarter> getAll();

	SubjectInQuarter getBySubjectQuarter(String subject, String quarter, String year);

	void removeSubject(String id);

	SubjectInQuarter getSubjectById(String id);

}
