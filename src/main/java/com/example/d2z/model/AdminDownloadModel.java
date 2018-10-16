package com.example.d2z.model;

import java.math.BigDecimal;

public class AdminDownloadModel {
	
	private String user_code;
	private String company_name;
	private BigDecimal gst_payable;
	private BigDecimal Total_sales_in_AUD;
	private BigDecimal Total_GST_Exclude_sales_in_AUD;
	
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public BigDecimal getGst_payable() {
		return gst_payable;
	}
	public void setGst_payable(BigDecimal gst_payable) {
		this.gst_payable = gst_payable;
	}
	public BigDecimal getTotal_sales_in_AUD() {
		return Total_sales_in_AUD;
	}
	public void setTotal_sales_in_AUD(BigDecimal total_sales_in_AUD) {
		Total_sales_in_AUD = total_sales_in_AUD;
	}
	public BigDecimal getTotal_GST_Exclude_sales_in_AUD() {
		return Total_GST_Exclude_sales_in_AUD;
	}
	public void setTotal_GST_Exclude_sales_in_AUD(BigDecimal total_GST_Exclude_sales_in_AUD) {
		Total_GST_Exclude_sales_in_AUD = total_GST_Exclude_sales_in_AUD;
	}
	
	
	
}
