package com.nexcode.examsystem.model.dtos;

import java.util.List;

public class CourseDto {
	private Long id;
	private String name;
	private String description;
	private List<UserDto>userDtos;
	private long percentage;
	public CourseDto() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UserDto> getUserDtos() {
		return userDtos;
	}
	public void setUserDtos(List<UserDto> userDtos) {
		this.userDtos = userDtos;
	}
	public long getPercentage() {
		return percentage;
	}
	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}
	
	
}
