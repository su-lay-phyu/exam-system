package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ExamOnlyResponse {
	
	private Long id;
	private String name;
	private String description;
	private Integer examDurationMinute;
	private String publishedDate;
	private Integer examTotalMark;
	private Integer noOfQuestion;
	private CourseResponse category;
	private LevelResponse level;
}
