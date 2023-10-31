package com.nexcode.examsystem.model.dtos;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor

public class ExamDto {
	private Long id;
	private String name;
	private String description;
	private Integer examDurationMinute;
	private Integer examTotalMark;
	private Integer noOfQuestion;
	private String publishedDate;
	private boolean isActive;
	private Boolean isPublished;
	private Long courseId;
	private Long levelId;
	private CourseDto course;
	private LevelDto level;
	private List<QuestionDto>questions;
}
