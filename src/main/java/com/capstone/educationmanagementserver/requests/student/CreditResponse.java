package com.capstone.educationmanagementserver.requests.student;

import lombok.Data;

@Data
public class CreditResponse {
	Integer compulsory;
	Integer completeCompulsory;
	Integer elective;
	Integer completeElective;
	Integer credit;
	Integer completeCredit;

	public CreditResponse() {
		this.compulsory = 0;
		this.completeCompulsory = 0;
		this.elective = 0;
		this.completeElective = 0;
		this.credit = 0;
		this.completeCredit = 0;
	}

}
