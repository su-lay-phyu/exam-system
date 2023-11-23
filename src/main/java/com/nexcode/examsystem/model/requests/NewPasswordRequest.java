package com.nexcode.examsystem.model.requests;

public class NewPasswordRequest {
	private String email;
	private String newpassword;
	
	
	public NewPasswordRequest() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

}
