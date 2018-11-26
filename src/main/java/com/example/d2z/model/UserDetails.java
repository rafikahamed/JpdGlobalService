package com.example.d2z.model;

import javax.validation.constraints.Email;

public class UserDetails {
	
	private String access_level;
	private String companyName;
	@Email(message="Invalid Email Address")
	private String email_addr;
	private String mgr_username;
	private String pass_word;
	private String user_code;
	private String username;
	private String legalName;
	private String arn_number;
	private String authrorizedConatct;
	private String phoneNumber;
	
	public String getAuthrorizedConatct() {
		return authrorizedConatct;
	}
	public void setAuthrorizedConatct(String authrorizedConatct) {
		this.authrorizedConatct = authrorizedConatct;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getArn_number() {
		return arn_number;
	}
	public void setArn_number(String arn_number) {
		this.arn_number = arn_number;
	}
	public String getAccess_level() {
		return access_level;
	}
	public void setAccess_level(String access_level) {
		this.access_level = access_level;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getEmail_addr() {
		return email_addr;
	}
	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}
	public String getMgr_username() {
		return mgr_username;
	}
	public void setMgr_username(String mgr_username) {
		this.mgr_username = mgr_username;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
}
