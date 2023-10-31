package com.nexcode.examsystem.model.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class VerifyOtpRequest { 
	private String email;
	private String otp;
}
