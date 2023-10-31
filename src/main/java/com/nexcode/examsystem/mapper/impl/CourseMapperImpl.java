package com.nexcode.examsystem.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.examsystem.mapper.CourseMapper;
import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.requests.CourseRequest;
import com.nexcode.examsystem.model.responses.CourseResponse;


@Component
public class CourseMapperImpl implements CourseMapper{
	

	@Override
	public CourseDto toDto(Course c) {
		if(c==null)
		{
			return null;
		}
		CourseDto dto = new CourseDto();
		dto.setId(c.getId());
		dto.setName(c.getName());
		dto.setDescription(c.getDescription());
		return dto;
	}
	
	@Override
	public List<CourseDto> toDtoList(List<Course> categories) {
		if(categories==null)
		{
			return null;
		}
		List<CourseDto>dtoList=categories.stream().map(category->toDto(category)).collect(Collectors.toList());
		return dtoList;
	}
	@Override
	public CourseDto toDto(CourseRequest request) {
	    if (request == null) 
	    {
	        return null;
	    }
	    CourseDto dto = new CourseDto();
	    dto.setName(request.getName()); 
        dto.setDescription(request.getDescription()); 
        return dto;
	}

	@Override
	public CourseResponse toResponse(CourseDto dto) {
		CourseResponse response=new CourseResponse();
		response.setId(dto.getId());
		response.setName(dto.getName());
		response.setDescription(dto.getDescription());
		return response;
	}

	@Override
	public List<CourseResponse> toResponseList(List<CourseDto> dtos) {
		return dtos.stream().map(c->toResponse(c)).collect(Collectors.toList());
	}

}
