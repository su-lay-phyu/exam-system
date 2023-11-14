package com.nexcode.examsystem.service;

import java.util.List;

import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.dtos.ExamDto;
import com.nexcode.examsystem.model.dtos.UserDto;

public interface CourseService {
	
	public List<CourseDto>getAllCourses();
	public CourseDto findByName(String name);
	public List<UserDto> getAllUserByCourseId(Long id);
	public List<ExamDto>getAllExamByCourseId(Long id);
	public List<ExamDto>getAllPublishedExams(Long id);
	public CourseDto addCourse(CourseDto dto);
	public CourseDto updateCourse(Long id,CourseDto dto);
}
