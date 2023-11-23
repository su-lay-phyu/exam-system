package com.nexcode.examsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.examsystem.model.requests.LoginRequest;
import com.nexcode.examsystem.model.responses.JwtResponse;
import com.nexcode.examsystem.service.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = authService.authenticate(loginRequest);
		return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
	}



	
}
