package com.nexcode.examsystem.model.requests;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionRequest {
	private String question;
	private List<AnswerRequest> answers;
	
}
