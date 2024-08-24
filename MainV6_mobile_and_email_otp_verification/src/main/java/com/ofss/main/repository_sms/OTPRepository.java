package com.ofss.main.repository_sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ofss.main.domain_sms.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long>{

	 OTP findByPhoneNumber(String phoneNumber);
}
