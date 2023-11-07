package com.nexcode.examsystem.model.responses;

import java.util.List;

public class UserAnswerResponse {

	private Long id;
	private String question;
	private List<CorrectAnswerResponse>correctAnswerRespons;
	private String selectedAnswer;
	private Boolean isSelectedAnswerCorrect;
}
