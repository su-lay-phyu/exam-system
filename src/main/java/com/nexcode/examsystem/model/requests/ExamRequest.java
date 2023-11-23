package com.nexcode.examsystem.model.requests;

import java.util.List;


public class ExamRequest {
	
	private String name;
	private String description;
	private Integer examDurationMinute;
	private Integer examTotalMark;
	private Integer noOfQuestion;
	private Long courseId;
	private Long levelId;
	private List<QuestionRequest>questions;
	
	public ExamRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getExamDurationMinute() {
		return examDurationMinute;
	}

	public void setExamDurationMinute(Integer examDurationMinute) {
		this.examDurationMinute = examDurationMinute;
	}

	public Integer getExamTotalMark() {
		return examTotalMark;
	}

	public void setExamTotalMark(Integer examTotalMark) {
		this.examTotalMark = examTotalMark;
	}

	public Integer getNoOfQuestion() {
		return noOfQuestion;
	}

	public void setNoOfQuestion(Integer noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public List<QuestionRequest> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionRequest> questions) {
		this.questions = questions;
	}
	
}
