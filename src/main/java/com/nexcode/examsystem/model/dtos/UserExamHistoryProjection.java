package com.nexcode.examsystem.model.dtos;

public interface UserExamHistoryProjection {

	public Long getId();
	public String getExamName();
	public String getExamDescription();
	public Integer getNumberOfQuestionsToGenerate();
	public Integer getExamTotalMark();
	public String getCourseName();
	public String getLevelName();
	public Integer getObtainedResult();
	public Boolean isPass();
}
