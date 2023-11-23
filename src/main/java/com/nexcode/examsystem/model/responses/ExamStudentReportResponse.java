package com.nexcode.examsystem.model.responses;

public class ExamStudentReportResponse {
	private Long id;
	private String rollNo;
	private String userName;
	private String email;
	private Integer obtainedMark;
	private Boolean PassFail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getObtainedMark() {
		return obtainedMark;
	}
	public void setObtainedMark(Integer obtainedMark) {
		this.obtainedMark = obtainedMark;
	}
	public Boolean getPassFail() {
		return PassFail;
	}
	public void setPassFail(Boolean passFail) {
		PassFail = passFail;
	}

}
