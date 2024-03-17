package com.capstone.educationmanagementserver.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capstone.educationmanagementserver.enums.Mandatory;
import com.capstone.educationmanagementserver.models.Block;
import com.capstone.educationmanagementserver.models.Result;
import com.capstone.educationmanagementserver.models.Subject;
import com.capstone.educationmanagementserver.models.SubjectInQuarter;
import com.capstone.educationmanagementserver.requests.block.AddBlock;

public interface IResultService {

	List<Result> getResultByStudent(String id);

	List<SubjectInQuarter> getSubjectInLastQuarterByStudent(String id);
}
