package com.nexcode.examsystem.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class UserExamHistoryResponse {

	private Long id;
	private Integer obtainedResult;
	private Boolean isPassFail;
	private Long examId;
	private String examName;
	private String examDescription;
	private Integer ExamTotalMark;
	private String Level;
	private List<UserAnswerResponse>userAnswerResponse;
}
