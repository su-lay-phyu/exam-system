package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorrectAnswerResponse {
	
	private String answer;
	private boolean correctAnswer;
}
