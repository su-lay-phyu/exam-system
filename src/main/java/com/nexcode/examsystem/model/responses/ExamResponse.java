package com.nexcode.examsystem.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ExamResponse {
	
	private Long id;
	private String name;
	private String description;
	private Integer examDurationMinute;
	private String publishedDate;
	private Integer examTotalMark;
	private Integer noOfQuestion;
	private CourseResponse category;
	private LevelResponse level;
	private List<QuestionResponse>questions;
}
