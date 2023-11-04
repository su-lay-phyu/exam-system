package com.nexcode.examsystem.service.impl;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.model.exception.UnauthorizedException;
import com.nexcode.examsystem.model.requests.LoginRequest;
import com.nexcode.examsystem.model.responses.JwtResponse;
import com.nexcode.examsystem.repository.UserRepository;
import com.nexcode.examsystem.security.JwtService;
import com.nexcode.examsystem.service.AuthService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;
	
	private final UserRepository userRepository;

	@Override
	public JwtResponse authenticate(LoginRequest loginRequest) {
		
		Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		User foundedUser = userRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(()->new BadRequestException("user not found"));
		boolean isFirstTime=foundedUser.getIsPasswordChanged();
		foundedUser.setActive(true);
		userRepository.save(foundedUser);
		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			
			String jwt = jwtService.generateToken(authentication);
			return new JwtResponse(jwt, expiredAt.toInstant().toString(),isFirstTime);
		}
		else
		{
			throw new UnauthorizedException("Email or Password Is wrong!");
		}
		
	}
}
