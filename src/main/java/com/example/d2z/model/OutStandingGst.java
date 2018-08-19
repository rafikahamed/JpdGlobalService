package com.example.d2z.model;

public class OutStandingGst {
	
	public Double importGst;
	public Double exportGst;
	public String lastCurrencyUpdatedTime;
	
	public String getLastCurrencyUpdatedTime() {
		return lastCurrencyUpdatedTime;
	}
	public void setLastCurrencyUpdatedTime(String lastCurrencyUpdatedTime) {
		this.lastCurrencyUpdatedTime = lastCurrencyUpdatedTime;
	}
	public Double getImportGst() {
		return importGst;
	}
	public void setImportGst(Double importGst) {
		this.importGst = importGst;
	}
	public Double getExportGst() {
		return exportGst;
	}
	public void setExportGst(Double exportGst) {
		this.exportGst = exportGst;
	}

}
