package com.nexcode.examsystem.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class QuestionResponse {
	private String question;
	private List<AnswerResponse> answers;
}
