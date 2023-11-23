package com.nexcode.examsystem.model.dtos;

import java.util.Date;
import java.util.List;

public class UserExamDto {

    private Long id;
    private Integer obtainedResult;
    private Date submittedTime;
    private boolean isPass;
    private UserDto user;
    private ExamDto exam;
    private List<UserAnswerDto> userAnswers;
	public UserExamDto() {
		
	}
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
	public Date getSubmittedTime() {
		return submittedTime;
	}
	public void setSubmittedTime(Date submittedTime) {
		this.submittedTime = submittedTime;
	}
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public ExamDto getExam() {
		return exam;
	}
	public void setExam(ExamDto exam) {
		this.exam = exam;
	}
	public List<UserAnswerDto> getUserAnswers() {
		return userAnswers;
	}
	public void setUserAnswers(List<UserAnswerDto> userAnswers) {
		this.userAnswers = userAnswers;
	}
    
    
}
