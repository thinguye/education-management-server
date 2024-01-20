package com.capstone.educationmanagementserver.requests.quarter;

import com.capstone.educationmanagementserver.models.Lecturer;
import com.capstone.educationmanagementserver.models.Quarter;
import com.capstone.educationmanagementserver.models.Subject;

import lombok.Data;

@Data
public class AddSubjectToQuarter {
	Quarter quarter;
	Subject subject;
	Lecturer lecturer;
}
