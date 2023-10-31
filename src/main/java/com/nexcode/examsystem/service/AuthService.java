package com.nexcode.examsystem.service;

import com.nexcode.examsystem.model.requests.LoginRequest;
import com.nexcode.examsystem.model.responses.JwtResponse;

public interface AuthService {
	
	public JwtResponse authenticate(LoginRequest loginRequest) ;
}
