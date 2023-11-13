package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StudentCourseResponse {
	private Long id;
	private String name;
	private String description;
	private Long percentage;
}
