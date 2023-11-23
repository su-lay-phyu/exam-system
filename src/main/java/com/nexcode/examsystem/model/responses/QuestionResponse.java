package com.nexcode.examsystem.model.responses;

import java.util.List;

public class QuestionResponse {
	private Long id;
	private String question;
	private List<AnswerResponse> answers;
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
	public List<AnswerResponse> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerResponse> answers) {
		this.answers = answers;
	}
	
}
