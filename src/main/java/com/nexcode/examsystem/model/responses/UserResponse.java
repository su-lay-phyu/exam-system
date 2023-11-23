package com.nexcode.examsystem.model.responses;

import java.util.List;

public class UserResponse {
	private Long id;
	private String rollNo;
	private String username;
	private String email;
	private String phone;
	private List<CourseResponse>categories;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<CourseResponse> getCategories() {
		return categories;
	}
	public void setCategories(List<CourseResponse> categories) {
		this.categories = categories;
	}
}
