package com.nexcode.examsystem.model.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class NewPasswordRequest {
	private String email;
	private String newpassword;

}
