package com.ofss.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.services.OTPVerificationService;

@RestController
@RequestMapping("/api/otp")
public class OTPVerifcationController {

	 private OTPVerificationService otpVerificationService;

	    @Autowired
	    public void OTPVerificationController(OTPVerificationService otpVerificationService) {
	        this.otpVerificationService = otpVerificationService;
	    }

    @PostMapping("/send")
    public String sendOtp(@RequestParam String phoneNumber) {
        otpVerificationService.sendOtp(phoneNumber);
        return "OTP sent";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String code) {
        boolean isValid = otpVerificationService.verifyOtp(phoneNumber, code);
        return isValid ? "OTP verified" : "Invalid OTP";
    }
}
