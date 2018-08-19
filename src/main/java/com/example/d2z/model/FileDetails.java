package com.example.d2z.model;

import java.math.BigInteger;
import java.util.Date;

public class FileDetails {
	
	private String username;
	private String user_code;
	public String GST_eligible= "GST";
	private String reference_no;
	private String arrival_date;
	private String currency_code;
	private Double amount;
	private Double GST_payable;
	private String report_indicator;
	private Date upload_date;
	private long txn_date;
	private String fileName;
	private BigInteger aud_converted_value;
	private String companyName;
	private String accessLevel;
	private String errMessage;
	private String successMsg;
	
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public BigInteger getAud_converted_value() {
		return aud_converted_value;
	}
	public void setAud_converted_value(BigInteger aud_converted_value) {
		this.aud_converted_value = aud_converted_value;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getGST_eligible() {
		return GST_eligible;
	}
	public void setGST_eligible(String gST_eligible) {
	    if (gST_eligible != null) { 
	    	GST_eligible = gST_eligible; 
	    }else {
	    	System.out.println("GST null---");
	    }
	}
	public String getReference_no() {
		return reference_no;
	}
	public void setReference_no(String reference_no) {
		this.reference_no = reference_no;
	}
	
	public String getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}
	public long getTxn_date() {
		return txn_date;
	}
	public void setTxn_date(long txn_date) {
		this.txn_date = txn_date;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getGST_payable() {
		return GST_payable;
	}
	public void setGST_payable(Double gST_payable) {
		GST_payable = gST_payable;
	}
	public String getReport_indicator() {
		return report_indicator;
	}
	public void setReport_indicator(String report_indicator) {
		this.report_indicator = report_indicator;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	
}
