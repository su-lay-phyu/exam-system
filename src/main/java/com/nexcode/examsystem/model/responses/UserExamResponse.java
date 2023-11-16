package com.nexcode.examsystem.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserExamResponse {

	private Long id;
	private Integer obtainedResult;
	private Boolean isPassFail;
	private ExamResponse examResponse;
	private List<UserAnswerResponse>userAnswerResponse;
}
