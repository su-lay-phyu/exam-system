package com.nexcode.examsystem.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserResponse {
	private Long id;
	private String rollNo;
	private String username;
	private String email;
	private String phone;
	private List<CourseResponse>categories;
}
