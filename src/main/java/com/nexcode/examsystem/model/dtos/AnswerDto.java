package com.nexcode.examsystem.model.dtos;

public class AnswerDto {
	
	private Long id;
	private String answer;
	private boolean isCorrectAnswer;
	
	public AnswerDto() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrectAnswer() {
		return isCorrectAnswer;
	}
	public void setCorrectAnswer(boolean isCorrectAnswer) {
		this.isCorrectAnswer = isCorrectAnswer;
	}
	
}
