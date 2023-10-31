package com.nexcode.examsystem.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Email "+email);
		User foundedUser = userRepository.findByEmail(email).orElseThrow(()->new BadRequestException("User Not Found : email->"+email));
		return UserPrincipal.build(foundedUser);
	}
}
