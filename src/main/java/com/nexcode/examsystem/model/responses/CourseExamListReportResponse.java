package com.nexcode.examsystem.model.responses;

public class CourseExamListReportResponse {
	
	private Long examId;
	private String examName;
	private String levelName;
	private String examPublishedDate;
	private Integer inProgressStudents;
	private Integer completedStudents;
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getExamPublishedDate() {
		return examPublishedDate;
	}
	public void setExamPublishedDate(String examPublishedDate) {
		this.examPublishedDate = examPublishedDate;
	}
	public Integer getInProgressStudents() {
		return inProgressStudents;
	}
	public void setInProgressStudents(Integer inProgressStudents) {
		this.inProgressStudents = inProgressStudents;
	}
	public Integer getCompletedStudents() {
		return completedStudents;
	}
	public void setCompletedStudents(Integer completedStudents) {
		this.completedStudents = completedStudents;
	}
	
	

}
