package com.example.d2z.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the data_console database table.
 * 
 */
@Entity
@Table(name="data_console")
@NamedQuery(name="DataConsole.findAll", query="SELECT d FROM DataConsole d")
@NamedStoredProcedureQueries({
	   @NamedStoredProcedureQuery(name = "delete_gst_data", 
	   procedureName = "deleteRecord",
	   parameters = {
		   @StoredProcedureParameter(mode = ParameterMode.IN, name = "reference_no", type = String.class)
		})
})

public class DataConsole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private BigDecimal amount;

	@Column(name="aud_converted_amount")
	private BigDecimal audConvertedAmount;

	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="file_name")
	private String fileName;

	@Column(name="GST_eligible")
	private String gstEligible;

	@Column(name="GST_payable")
	private BigDecimal gstPayable;

	@Column(name="reference_no")
	private String referenceNo;

	@Column(name="report_indicator")
	private String reportIndicator;

	@Temporal( TemporalType.DATE )
	@Column(name="sale_date")
	private java.util.Date saleDate;

	@Temporal( TemporalType.DATE )
	@Column(name="txn_date")
	private Date txnDate;

	@Temporal( TemporalType.DATE )	
	@Column(name="upload_date")
	private Date uploadDate;

	@Column(name="user_code")
	private String userCode;
	
	@Column(name="company_name")
	private String companyName;

	private String username;
	
	private String isdeleted;

	public DataConsole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAudConvertedAmount() {
		return audConvertedAmount;
	}

	public void setAudConvertedAmount(BigDecimal audConvertedAmount) {
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

	public String getGstEligible() {
		return this.gstEligible;
	}

	public void setGstEligible(String gstEligible) {
		this.gstEligible = gstEligible;
	}

	public BigDecimal getGstPayable() {
		return this.gstPayable;
	}

	public void setGstPayable(BigDecimal gstPayable) {
		this.gstPayable = gstPayable;
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

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public Date getUploadDate() {
		return uploadDate;
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
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}

}