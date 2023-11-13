package com.nexcode.examsystem.model.projections;

public interface UserExamHistoryProjection {

	public Long getId();
	public String getExamName();
	public String getExamDescription();
	public Integer getNoOfQuestion();
	public Integer getExamTotalMark();
	public String getCourseName();
	public String getLevelName();
	public Integer getObtainedResult();
	public Boolean getIsPassFail();
}
