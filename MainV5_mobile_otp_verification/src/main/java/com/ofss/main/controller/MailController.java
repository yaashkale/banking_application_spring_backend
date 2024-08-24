package com.ofss.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ofss.main.services.MailSenderService;

@RequestMapping("mailingService")
@RestController
public class MailController {
	
	@Autowired
	MailSenderService mailSenderService;
	
	@PostMapping("sendOTP")
	public boolean mailSendOTP(@RequestBody Map<String, String> payload) {
		try {
			String to = payload.get("to");
			String subject = "Your OTP Code";
            String body = "Dear user,\n\nYour OTP code is: {otp}\n\nPlease use this code to complete your verification.\n\nThank you.";
            
			mailSenderService.sendNewMail(to, subject, body);
			return true;
		}
		catch(Exception e) {
			System.out.println("Error in the MailSenderService");
			e.printStackTrace();
			return false;
		}
		
	}
	
}
