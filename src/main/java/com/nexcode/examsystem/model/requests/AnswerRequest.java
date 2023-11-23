package com.nexcode.examsystem.model.requests;

public class AnswerRequest {
	private String answer;
	private boolean correctAnswer;
	
	public AnswerRequest() {
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
