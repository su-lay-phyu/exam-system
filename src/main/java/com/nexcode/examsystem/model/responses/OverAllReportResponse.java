package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class OverAllReportResponse {
	private String courseName;
	private Integer totalNoOfStudents;
	private Integer inProgressStudents;
	private Integer completeStudents;
}
