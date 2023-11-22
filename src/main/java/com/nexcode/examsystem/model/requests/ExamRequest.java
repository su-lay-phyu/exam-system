package com.nexcode.examsystem.model.requests;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExamRequest {
	
	private String name;
	private String description;
	private Integer examDurationMinute;
	private Integer examTotalMark;
	private Integer noOfQuestion;
	private Long courseId;
	private Long levelId;
	private List<QuestionRequest>questions;
}
