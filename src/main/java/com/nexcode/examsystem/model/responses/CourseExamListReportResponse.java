package com.nexcode.examsystem.model.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseExamListReportResponse {
	
	private Long examId;
	private String examName;
	private String levelName;
	private String examPublishedDate;
	private Integer inProgressStudents;
	private Integer completedStudents;

}
