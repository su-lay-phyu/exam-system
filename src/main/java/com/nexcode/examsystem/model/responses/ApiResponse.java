package com.nexcode.examsystem.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
	
	private Boolean success;
	private String message;
	

}
