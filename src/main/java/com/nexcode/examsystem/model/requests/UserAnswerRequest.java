package com.nexcode.examsystem.model.requests;

public class UserAnswerRequest {
	
	private Long questionId;
	private String selectedAnswer;
	
	public UserAnswerRequest() {
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	
}
