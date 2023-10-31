package com.nexcode.examsystem.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtResponse {
	private String token;
	private String expiredAt;
	private boolean isFirstPasswordChangeCompleted;
}
