package com.nexcode.examsystem.model.responses;

public class JwtResponse {
	private String username;
	private String token;
	private String expiredAt;
	private boolean isFirstPasswordChangeCompleted;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public JwtResponse(String username, String token, String expiredAt, boolean isFirstPasswordChangeCompleted) {
		this.username = username;
		this.token = token;
		this.expiredAt = expiredAt;
		this.isFirstPasswordChangeCompleted = isFirstPasswordChangeCompleted;
	}
	
}
