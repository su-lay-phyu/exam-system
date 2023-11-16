package com.nexcode.examsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nexcode.examsystem.security.JwtAuthenticationFilter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Configuration
@EnableWebSecurity
@Getter
@Setter
@RequiredArgsConstructor
public class WebSecurityConfigure {
	
	private final AuthenticationEntryPoint authenticationEntryPoint;
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) throws Exception
	{
		 AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
	     builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	     return builder.build();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeHttpRequests(
				authorize -> authorize.antMatchers(HttpMethod.POST,
			            "/api/auth/login",
			            "/api/user/forgot-password",
			            "/api/user/verified-otp",
			            "/api/user/set-new-password")
			            .permitAll()
				.antMatchers(HttpMethod.POST,"/api/user/student-signup").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/user/change-password").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
