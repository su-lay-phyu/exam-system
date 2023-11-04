package com.nexcode.examsystem.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class QuestionAnswerResponse {
	private Long id;
	private String question;
	private List<CorrectAnswerResponse> answers;
}
