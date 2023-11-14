package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ExamStudentReportResponse {
	private Long id;
	private String rollNo;
	private String userName;
	private String email;
	private Integer obtainedMark;
	private Boolean PassFail;

}
