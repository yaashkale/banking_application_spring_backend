package com.ofss.main.services;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {
	void sendNewMail(String to, String subject, String body);
}
