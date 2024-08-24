package com.ofss.main.services;

public interface OTPVerificationService {
    void sendOtp(String phoneNumber);
    boolean verifyOtp(String phoneNumber, String code);
}
