package com.nexcode.examsystem.model.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long id;
	private String rollNo;
	private String username;
	private String email;
	private String password;
	private String phone;
	private List<RoleDto>roles;
	private String otp;
	private Date otpRequestedTime;
	private List<Long>ids;
	private List<CourseDto>categories;
	public UserDto(String rollNo, String username, String email, String password, List<RoleDto> roles,
			List<CourseDto> categories) {
		super();
		this.rollNo = rollNo;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.categories = categories;
	}
	public UserDto(String rollNo, String username, String email) {
		super();
		this.rollNo = rollNo;
		this.username = username;
		this.email = email;
	}
}
