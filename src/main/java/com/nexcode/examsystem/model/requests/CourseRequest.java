package com.nexcode.examsystem.model.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseRequest {
	
	private String name;
	private String description;
}
