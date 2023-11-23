package com.nexcode.examsystem.model.dtos;

public class UserAnswerDto {

	private Long id;
	private QuestionDto question;
	private String selectedAnswer;
	private boolean isSelectedAnswerCorrect;
	private UserExamDto userExamDto;
	
	public UserAnswerDto() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public QuestionDto getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDto question) {
		this.question = question;
	}
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public boolean isSelectedAnswerCorrect() {
		return isSelectedAnswerCorrect;
	}
	public void setSelectedAnswerCorrect(boolean isSelectedAnswerCorrect) {
		this.isSelectedAnswerCorrect = isSelectedAnswerCorrect;
	}
	public UserExamDto getUserExamDto() {
		return userExamDto;
	}
	public void setUserExamDto(UserExamDto userExamDto) {
		this.userExamDto = userExamDto;
	}
	
	
}
