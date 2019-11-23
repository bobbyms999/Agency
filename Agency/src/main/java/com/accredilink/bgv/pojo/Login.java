package com.accredilink.bgv.pojo;

import java.io.Serializable;

public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7996870187145646825L;
	
	private String emailId;
	private String password;
	private String token;
	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Login [emailId=" + emailId + ", password=" + password + ", token=" + token + "]";
	}
	
}
