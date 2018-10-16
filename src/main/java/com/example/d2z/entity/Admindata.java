package com.example.d2z.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Admindata database table.
 * 
 */
@Entity
@Table(name="Admindata")
@NamedQuery(name="Admindata.findAll", query="SELECT a FROM Admindata a")
public class Admindata implements Serializable {
	private static final long serialVersionUID = 1L;

	private String password;
	
	@Id
	private String username;

	public Admindata() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}