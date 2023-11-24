package com.nexcode.examsystem.model.dtos;

import java.util.Date;
import java.util.List;

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
	private List<CourseDto>courses;
	public UserDto() {
		
	}
	
	public UserDto(String rollNo, String username, String email, String phone, List<RoleDto> roles,
			List<CourseDto> courses) {
		super();
		this.rollNo = rollNo;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}
	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public List<CourseDto> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseDto> courses) {
		this.courses = courses;
	}
	
}
