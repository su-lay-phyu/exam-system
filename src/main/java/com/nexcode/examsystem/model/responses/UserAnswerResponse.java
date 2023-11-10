package com.nexcode.examsystem.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAnswerResponse {

	private Long id;
	private QuestionResponse questionResponse;
	private String selectedAnswer;
	private Boolean isSelectedAnswerCorrect;
}
