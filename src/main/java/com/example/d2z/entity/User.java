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

	@Column(name="access_level")
	private String accessLevel;

	@Column(name="authorized_contact")
	private String authorizedContact;

	@Column(name="business_type")
	private String businessType;

	private String country;

	@Column(name="creation_date")
	private Date creationDate;

	@Column(name="email_addr")
	private String emailAddr;

	@Column(name="legal_name")
	private String legalName;

	@Column(name="mgr_username")
	private String mgrUsername;

	@Column(name="pass_word")
	private String passWord;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="post_code")
	private String postCode;

	@Column(name="postal_address")
	private String postalAddress;

	@Column(name="sub_urb")
	private String subUrb;

	@Column(name="tan_number")
	private String tanNumber;

	@Column(name="user_code")
	private String userCode;

	private String username;

	@Column(name="website_name")
	private String websiteName;

	public User() {
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
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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