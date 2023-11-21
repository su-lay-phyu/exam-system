package com.nexcode.examsystem.security;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class JwtService {

	@Value("${app.jwtSecret}")
	private String secretKey;

	public Claims resolveClaims(HttpServletRequest request) {

		String jwt = getJwt(request);

		JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);
		try {
			if (jwt != null) {

				Claims claims = jwtParser.parseClaimsJws(jwt).getBody();
				return claims;

			}
		} catch (SignatureException e) {
			log.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty -> Message: {}", e);
		}

		return null;
	}
	public String createJwtToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		final String authorities = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		return Jwts.builder().setId(Long.toString(userPrincipal.getId())).setSubject((userPrincipal.getEmail()))
				.claim("username", userPrincipal.getUsername())
				.claim("roles", authorities).setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();

	}
	public String getJwt(HttpServletRequest request) {

		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			return header.replace("Bearer ", "");
		}
		return null;
	}
}
