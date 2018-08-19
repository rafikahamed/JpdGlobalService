package com.example.d2z.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name="Access_level")
	private String access_level;

	@Column(name="Authorized_contact")
	private String authorized_contact;

	@Column(name="Business_type")
	private String business_type;

	@Column(name="Country")
	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name="Creation_date")
	private Date creation_date;

	@Column(name="Email_addr")
	private String email_addr;

	@Column(name="Legal_name")
	private String legal_name;

	@Column(name="Mgr_username")
	private String mgr_username;

	@Column(name="Pass_word")
	private String pass_word;

	@Column(name="Phone_number")
	private String phone_number;

	@Column(name="Post_code")
	private String post_code;

	@Column(name="Postal_address")
	private String postal_address;

	@Column(name="Sub_urb")
	private String sub_urb;

	private String TAN_number;

	@Column(name="user_code")
	private String userCode;

	private String username;

	@Column(name="Website_name")
	private String website_name;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess_level() {
		return this.access_level;
	}

	public void setAccess_level(String access_level) {
		this.access_level = access_level;
	}

	public String getAuthorized_contact() {
		return this.authorized_contact;
	}

	public void setAuthorized_contact(String authorized_contact) {
		this.authorized_contact = authorized_contact;
	}

	public String getBusiness_type() {
		return this.business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreation_date() {
		return this.creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getEmail_addr() {
		return this.email_addr;
	}

	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}

	public String getLegal_name() {
		return this.legal_name;
	}

	public void setLegal_name(String legal_name) {
		this.legal_name = legal_name;
	}

	public String getMgr_username() {
		return this.mgr_username;
	}

	public void setMgr_username(String mgr_username) {
		this.mgr_username = mgr_username;
	}

	public String getPass_word() {
		return this.pass_word;
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getPhone_number() {
		return this.phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPost_code() {
		return this.post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getPostal_address() {
		return this.postal_address;
	}

	public void setPostal_address(String postal_address) {
		this.postal_address = postal_address;
	}

	public String getSub_urb() {
		return this.sub_urb;
	}

	public void setSub_urb(String sub_urb) {
		this.sub_urb = sub_urb;
	}

	public String getTAN_number() {
		return this.TAN_number;
	}

	public void setTAN_number(String TAN_number) {
		this.TAN_number = TAN_number;
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

	public String getWebsite_name() {
		return this.website_name;
	}

	public void setWebsite_name(String website_name) {
		this.website_name = website_name;
	}

}