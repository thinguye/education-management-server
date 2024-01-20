package com.capstone.educationmanagementserver.controllers;

import com.capstone.educationmanagementserver.email.EmailDetails;
import com.capstone.educationmanagementserver.email.IEmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	@Autowired
	private IEmailService emailService;

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		return emailService.sendSimpleMail(details);
	}
}
