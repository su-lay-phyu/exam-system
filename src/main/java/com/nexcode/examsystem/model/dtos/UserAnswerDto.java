package com.nexcode.examsystem.model.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserAnswerDto {

	private Long id;
	private QuestionDto question;
	private String selectedAnswer;
	private boolean isSelectedAnswerCorrect;
	private UserExamDto userExamDto;
}
