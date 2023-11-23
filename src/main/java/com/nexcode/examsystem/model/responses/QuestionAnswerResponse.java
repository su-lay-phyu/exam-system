package com.nexcode.examsystem.model.responses;

import java.util.List;

public class QuestionAnswerResponse {
	private Long id;
	private String question;
	private List<CorrectAnswerResponse> answers;
	
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
	public List<CorrectAnswerResponse> getAnswers() {
		return answers;
	}
	public void setAnswers(List<CorrectAnswerResponse> answers) {
		this.answers = answers;
	}
	
	
}
