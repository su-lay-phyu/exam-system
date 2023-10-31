package com.nexcode.examsystem.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
	
	private Long id;
	private String answer;
	private boolean isCorrectAnswer;	
}
