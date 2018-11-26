package com.example.d2z.model;

import javax.validation.constraints.Email;

public class ArnRegistration {
	private String businessType;
	private String legalName;
	private String authrorizedConatct;
	private String phoneNumber;
	private String postalAddress;
	@Email(message="Invalid Email Address")
	private String emailAddress;
	private String subUrb;
	private String postCode;
	private String country;
	private String websiteName;
	private String tanNumber;
	private String message;
	private String dateOfBirth;
	private String registrationDate;
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
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
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getSubUrb() {
		return subUrb;
	}
	public void setSubUrb(String subUrb) {
		this.subUrb = subUrb;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public String getTanNumber() {
		return tanNumber;
	}
	public void setTanNumber(String tanNumber) {
		this.tanNumber = tanNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	

}
