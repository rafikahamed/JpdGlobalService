package com.example.d2z.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double AUD_currency_rate;
	
	@Id
	private String country;

	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="currency_rate")
	private Double currencyRate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated")
	private Date lastUpdated;

	public Currency() {
	}

	public Double getAUD_currency_rate() {
		return AUD_currency_rate;
	}


	public void setAUD_currency_rate(Double aUD_currency_rate) {
		AUD_currency_rate = aUD_currency_rate;
	}


	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}


	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}