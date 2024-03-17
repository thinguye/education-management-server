package com.capstone.educationmanagementserver.requests.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NumberStudentResponse {
	Integer total;
	Double gpaAverage;
	List<Integer> noStudentAll;
	List<Integer> noStudents;
	List<Integer> passStudents;
	List<Integer> failStudents;
	List<Integer> notGradeStudents;
	List<String> subjects;
	List<Double> gpa;

	public NumberStudentResponse(Integer total, Double gpaAverage, List<Integer> noStudentAll, List<Integer> noStudents,
			List<Integer> passStudents, List<Integer> failStudents,List<Integer> notGradeStudents, List<String> subjects, List<Double> gpa) {
		this.total = total;
		this.gpaAverage = gpaAverage;
		this.noStudentAll = noStudentAll;
		this.noStudents = noStudents;
		this.passStudents = passStudents;
		this.failStudents = failStudents;
		this.notGradeStudents = notGradeStudents;
		this.subjects = subjects;
		this.gpa = gpa;
	}
}
