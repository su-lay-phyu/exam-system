package com.nexcode.examsystem.model.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
	private Long id;
	private String name;
	private String description;
	private List<UserDto>userDtos;
	private Long percentage;
}
