package com.capstone.educationmanagementserver.requests.subject;

import lombok.Data;

@Data
public class UpdateCredit {
	String id;
	Integer theoryCredit;
	Integer labCredit;
}
