package com.nexcode.examsystem.mapper;

import java.util.List;

import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.requests.CourseRequest;
import com.nexcode.examsystem.model.responses.CourseResponse;

public interface CourseMapper {

	public CourseDto toDto(Course c) ;
	public List<CourseDto> toDtoList(List<Course> categories) ;
	public CourseDto toDto(CourseRequest request);
	public CourseResponse toResponse(CourseDto dto);
	public List<CourseResponse>toResponseList(List<CourseDto>dtos);
}
