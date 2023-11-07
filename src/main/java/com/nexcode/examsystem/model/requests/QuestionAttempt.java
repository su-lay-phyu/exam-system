package com.nexcode.examsystem.model.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionAttempt {
	
	private Long questionId;
	private String selectedAnswer;

}
