package com.example.d2z.dao;

import java.util.List;
import com.example.d2z.entity.Currency;
import com.example.d2z.entity.DataConsole;
import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.CurrencyDetails;
import com.example.d2z.model.FileDetails;
import com.example.d2z.model.OutStandingGst;
import com.example.d2z.model.UserDetails;

public interface LogisticsDao {

	public String login(String userName, String passWord);

	public String singUp(UserDetails userData);

	public String importParcel(List<FileDetails> fileData);

	public String userCheck(String username);

	public List<Currency> currencyDetails();

	public String exportParcel(List<FileDetails> fileData);

	public List<DataConsole> downloadDetails(String quater, String fileType, String userCode);

	public List<CurrencyDetails> currencyRate();

	public String loginDetails(String username);

	public String accessLevel(String userName);

	public String arnRegistration(List<ArnRegistration> arnRegisterData);

	public List<String> companyDetails();

	public OutStandingGst outStandingGst(String reportIndicator, String userCode);

	public List<String> adminDownloadDetails();

	public String adminLogin(String userName, String passWord);

	public List<String> fileNames(String userName);

	public List<DataConsole> importExportDetails(String fileName);

	public String deleteGstData(String reference_no);

	public List<String> fetchMgrUserName();
	
}
