package com.nexcode.examsystem.model.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	private String username;
	private String email;
	private String phone;
	private List<Long>courses;
}
