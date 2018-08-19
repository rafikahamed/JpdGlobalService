package com.example.d2z.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * The persistent class for the data_console database table.
 * 
 */
@Entity
@Table(name="data_console")
@NamedQuery(name="DataConsole.findAll", query="SELECT d FROM DataConsole d")
public class DataConsole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private BigInteger amount;

	@Temporal(TemporalType.DATE)
	@Column(name="sale_date")
	private Date saleDate;

	@Column(name="aud_converted_amount")
	private BigInteger audConvertedAmount;

	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="file_name")
	private String fileName;

	private String GST_eligible;

	private BigDecimal GST_payable;

	@Column(name="reference_no")
	private String referenceNo;

	@Column(name="report_indicator")
	private String reportIndicator;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="txn_date")
	private Date txnDate;

	@Temporal(TemporalType.DATE)
	@Column(name="upload_date")
	private Date uploadDate;

	@Column(name="user_code")
	private String userCode;

	private String username;

	public DataConsole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getAmount() {
		return this.amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public BigInteger getAudConvertedAmount() {
		return this.audConvertedAmount;
	}

	public void setAudConvertedAmount(BigInteger audConvertedAmount) {
		this.audConvertedAmount = audConvertedAmount;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getGST_eligible() {
		return this.GST_eligible;
	}

	public void setGST_eligible(String GST_eligible) {
		this.GST_eligible = GST_eligible;
	}
	
	public BigDecimal getGST_payable() {
		return GST_payable;
	}

	public void setGST_payable(BigDecimal gST_payable) {
		GST_payable = gST_payable;
	}

	public String getReferenceNo() {
		return this.referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getReportIndicator() {
		return this.reportIndicator;
	}

	public void setReportIndicator(String reportIndicator) {
		this.reportIndicator = reportIndicator;
	}

	public Date getTxnDate() {
		return this.txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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
	
	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}


}