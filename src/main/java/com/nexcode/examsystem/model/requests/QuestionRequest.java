package com.nexcode.examsystem.model.requests;

import java.util.List;


public class QuestionRequest {
	private String question;
	private List<AnswerRequest> answers;
	
	public QuestionRequest() {
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswerRequest> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerRequest> answers) {
		this.answers = answers;
	}
	
}
