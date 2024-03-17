package com.capstone.educationmanagementserver.requests.response;

import lombok.Data;

@Data
public class Undergraduate {
	Integer ielts;
	Integer qp;
	Integer credit;
	Integer graduated;
	Integer leaved;
	Integer undergraduate;
	Integer waiting;
	Integer qpButDone;
	Integer ieltsButDone;
	
	public Undergraduate(Integer ielts, Integer qp, Integer credit, Integer graduated, Integer leaved,
			Integer undergraduate, Integer waiting, Integer qpButDone, Integer ieltsButDone) {
		this.ielts = ielts;
		this.qp = qp;
		this.credit = credit;
		this.graduated = graduated;
		this.leaved = leaved;
		this.undergraduate = undergraduate;
		this.waiting = waiting;
		this.qpButDone = qpButDone;
		this.ieltsButDone = ieltsButDone;
	}
}
