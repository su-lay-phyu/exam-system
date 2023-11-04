package com.nexcode.examsystem.security;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${app.jwtSecret}")
	private String secretKey;

	public Claims resolveClaims(HttpServletRequest request)
	{
		JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);
		String token = getToken(request);
		try {
			if (token != null) {
				Claims claims = jwtParser.parseClaimsJws(token).getBody();
				return claims;
			}
			return null;
		} catch (ExpiredJwtException ex) {

			request.setAttribute("expired", ex.getMessage());
			throw ex;
		}
	}
	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal(); 
		String authorities = userPrincipal.getAuthorities().stream()
			    .map(GrantedAuthority::getAuthority)
			    .collect(Collectors.joining(","));
		
		String jwtString = Jwts.builder().setId(Long.toString(userPrincipal.getId()))
				.setSubject(userPrincipal.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000))
				.claim("roles", authorities)
				.claim("username", userPrincipal.getUsername())
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
		return jwtString;
	}

	private String getToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}

}
