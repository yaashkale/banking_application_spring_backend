package com.ofss.main.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImp implements MailSenderService{
	@Autowired
    private JavaMailSender mailSender;

    public void sendNewMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        
        String otp = String.format("%06d", new Random().nextInt(999999));
        String finalBody = body.replace("{otp}", otp);
        
        message.setText(finalBody);
        
        mailSender.send(message);
    }
}
