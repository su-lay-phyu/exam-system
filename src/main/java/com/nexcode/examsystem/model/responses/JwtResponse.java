package com.nexcode.examsystem.model.responses;

public class JwtResponse {
	private String token;
	private String expiredAt;
	private boolean isFirstPasswordChangeCompleted;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
	}
	public boolean isFirstPasswordChangeCompleted() {
		return isFirstPasswordChangeCompleted;
	}
	public void setFirstPasswordChangeCompleted(boolean isFirstPasswordChangeCompleted) {
		this.isFirstPasswordChangeCompleted = isFirstPasswordChangeCompleted;
	}
	public JwtResponse(String token, String expiredAt, boolean isFirstPasswordChangeCompleted) {
		super();
		this.token = token;
		this.expiredAt = expiredAt;
		this.isFirstPasswordChangeCompleted = isFirstPasswordChangeCompleted;
	}
	
	
}
