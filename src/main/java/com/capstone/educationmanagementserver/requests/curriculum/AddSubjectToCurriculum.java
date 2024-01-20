package com.capstone.educationmanagementserver.requests.curriculum;

import java.util.List;

import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;
@Data
public class AddSubjectToCurriculum {
	String id;
	List<Subject> subjects;
}
