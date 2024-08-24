package com.ofss.main.domain_sms;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType
    .IDENTITY)
    private Long id;

    private String phoneNumber;
    private String otpCode;
    public OTP() {
    	
    }
    public OTP(Long id, String phoneNumber, String otpCode) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.otpCode = otpCode;
	}

	// Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}
