package com.example.d2z.service;

import java.util.List;

import com.example.d2z.entity.Currency;
import com.example.d2z.entity.DataConsole;
import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.CurrencyDetails;
import com.example.d2z.model.FileDetails;
import com.example.d2z.model.OutStandingGst;
import com.example.d2z.model.UserDetails;
import com.example.d2z.model.UserMessage;

public interface LogisticsService {

	public UserMessage login(String userName, String passWord);

	public UserMessage singUp(UserDetails userData);

	public List<FileDetails> importParcel(List<FileDetails> fileData);

	public List<Currency> currencyDetails();
	
	public List<String> companyDetails();

	public List<FileDetails> exportParcel(List<FileDetails> fileData);

	public List<DataConsole> downloadDetails(String quater, String fileType, String userCode);

	public List<CurrencyDetails> currencyRate();

	public String accessLevel(String companyName, String userCode);

	public List<ArnRegistration> arnRegistration(List<ArnRegistration> arnRegisterData);

	public OutStandingGst outStandingGst(String reportIndicator, String userCode);

}
