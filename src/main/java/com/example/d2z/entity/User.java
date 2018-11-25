package com.example.d2z.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="Access_level")
	private String accessLevel;

	@Column(name="Authorized_contact")
	private String authorizedContact;

	@Column(name="Business_type")
	private String businessType;

	private String Country;

	@Column(name="Creation_date")
	private Date creationDate;

	@Column(name="Email_addr")
	private String emailAddr;

	@Column(name="Legal_name")
	private String legalName;

	@Column(name="Mgr_username")
	private String mgrUsername;

	@Column(name="Pass_word")
	private String passWord;

	@Column(name="Phone_number")
	private String phoneNumber;

	@Column(name="Post_code")
	private String postCode;

	@Column(name="Postal_address")
	private String postalAddress;

	@Column(name="Sub_urb")
	private String subUrb;

	@Column(name="TAN_number")
	private String tanNumber;

	@Column(name="user_code")
	private String userCode;

	private String username;

	@Column(name="Website_name")
	private String websiteName;
	
	@Column(name="Registration_date")
	private String registration_date;
	
	@Column(name="DOB")
	private String dob;

	public User() {
	}
	
	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessLevel() {
		return this.accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getAuthorizedContact() {
		return this.authorizedContact;
	}

	public void setAuthorizedContact(String authorizedContact) {
		this.authorizedContact = authorizedContact;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCountry() {
		return this.Country;
	}

	public void setCountry(String country) {
		this.Country = country;
	}

	public Object getCreationDate() {
		return this.creationDate;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getLegalName() {
		return this.legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getMgrUsername() {
		return this.mgrUsername;
	}

	public void setMgrUsername(String mgrUsername) {
		this.mgrUsername = mgrUsername;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPostalAddress() {
		return this.postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getSubUrb() {
		return this.subUrb;
	}

	public void setSubUrb(String subUrb) {
		this.subUrb = subUrb;
	}

	public String getTanNumber() {
		return this.tanNumber;
	}

	public void setTanNumber(String tanNumber) {
		this.tanNumber = tanNumber;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWebsiteName() {
		return this.websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

}