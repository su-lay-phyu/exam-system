package com.nexcode.examsystem.model.responses;

import java.util.List;


public class UserExamResponse {

	private Long id;
	private Integer obtainedResult;
	private Boolean isPassFail;
	private ExamResponse examResponse;
	private List<UserAnswerResponse>userAnswerResponse;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getObtainedResult() {
		return obtainedResult;
	}
	public void setObtainedResult(Integer obtainedResult) {
		this.obtainedResult = obtainedResult;
	}
	public Boolean getIsPassFail() {
		return isPassFail;
	}
	public void setIsPassFail(Boolean isPassFail) {
		this.isPassFail = isPassFail;
	}
	public ExamResponse getExamResponse() {
		return examResponse;
	}
	public void setExamResponse(ExamResponse examResponse) {
		this.examResponse = examResponse;
	}
	public List<UserAnswerResponse> getUserAnswerResponse() {
		return userAnswerResponse;
	}
	public void setUserAnswerResponse(List<UserAnswerResponse> userAnswerResponse) {
		this.userAnswerResponse = userAnswerResponse;
	}
	
	
}
