package com.ofss.main.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

import jakarta.annotation.PostConstruct;

@Service
public class OTPVerificationServiceImp implements OTPVerificationService {

    private static final Logger logger = LoggerFactory.getLogger(OTPVerificationServiceImp.class);

    @Value("${twilio.verify.service.sid}")
    private String verifyServiceSid;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendOtp(String phoneNumber) {
        String formattedPhoneNumber = formatPhoneNumber(phoneNumber);
        logger.info("Sending OTP to: {}", formattedPhoneNumber);

        Verification verification = Verification.creator(
                verifyServiceSid,
                formattedPhoneNumber,
                "sms"
        ).create();

        logger.info("OTP sent successfully, SID: {}", verification.getSid());
    }

    @Override
    public boolean verifyOtp(String phoneNumber, String code) {
        String formattedPhoneNumber = formatPhoneNumber(phoneNumber);
        logger.info("Verifying OTP for: {}", formattedPhoneNumber);

        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(
                    verifyServiceSid,
                    code
            ).setTo(formattedPhoneNumber)
            .create();

            boolean isApproved = "approved".equals(verificationCheck.getStatus());
            logger.info("OTP verification status: {}", verificationCheck.getStatus());
            return isApproved;
        } catch (Exception e) {
            logger.error("Error during OTP verification", e);
            return false;
        }
    }

    private String formatPhoneNumber(String phoneNumber) {
        // Ensure phone number is in E.164 format
        if (phoneNumber != null && !phoneNumber.startsWith("+")) {
            if (phoneNumber.startsWith("91")) {
                phoneNumber = "+" + phoneNumber;
            } else {
                phoneNumber = "+91" + phoneNumber; // Default to Indian country code if not specified
            }
        }
        return phoneNumber;
    }
}
