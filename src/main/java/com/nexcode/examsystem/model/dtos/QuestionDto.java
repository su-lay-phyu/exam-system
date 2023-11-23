package com.nexcode.examsystem.model.dtos;

import java.util.List;

public class QuestionDto {
	
    private Long id;
    private String question;
    private Long examId;
    private List<AnswerDto>answerDtos;
	public QuestionDto() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public List<AnswerDto> getAnswerDtos() {
		return answerDtos;
	}
	public void setAnswerDtos(List<AnswerDto> answerDtos) {
		this.answerDtos = answerDtos;
	}
    
    
}
