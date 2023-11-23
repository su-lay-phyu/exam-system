package com.nexcode.examsystem.model.responses;

public class UserAnswerResponse {

	private Long id;
	private QuestionResponse questionResponse;
	private String selectedAnswer;
	private Boolean isSelectedAnswerCorrect;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public QuestionResponse getQuestionResponse() {
		return questionResponse;
	}
	public void setQuestionResponse(QuestionResponse questionResponse) {
		this.questionResponse = questionResponse;
	}
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public Boolean getIsSelectedAnswerCorrect() {
		return isSelectedAnswerCorrect;
	}
	public void setIsSelectedAnswerCorrect(Boolean isSelectedAnswerCorrect) {
		this.isSelectedAnswerCorrect = isSelectedAnswerCorrect;
	}
	
	
}
