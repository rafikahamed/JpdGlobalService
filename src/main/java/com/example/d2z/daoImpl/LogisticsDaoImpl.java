package com.example.d2z.daoImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.d2z.dao.LogisticsDao;
import com.example.d2z.entity.Currency;
import com.example.d2z.entity.DataConsole;
import com.example.d2z.entity.User;
import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.CurrencyDetails;
import com.example.d2z.model.CurrencyPojo;
import com.example.d2z.model.FileDetails;
import com.example.d2z.model.OutStandingGst;
import com.example.d2z.model.UserDetails;
import com.example.d2z.repository.AdminRepository;
import com.example.d2z.repository.CurrencyRepository;
import com.example.d2z.repository.FileDetailsRepository;
import com.example.d2z.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LogisticsDaoImpl implements LogisticsDao{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FileDetailsRepository fileRepository;
	
	@Autowired
	CurrencyRepository currencyRepositry;
	
	@Autowired
	AdminRepository adminRepositry;
	
	@Override
	public String login(String userName, String passWord) {
		String loginSuccess = null;
		loginSuccess = userRepository.findUserByUserCredentails(userName,passWord);
		return loginSuccess;
	}
	
	@Override
	public String loginDetails(String userName) {
		String loginSuccess = null;
		loginSuccess = userRepository.findUserByUserName(userName);
		return loginSuccess;
	}

	@Override
	public String singUp(UserDetails userData) {
		String message = null;
		int signUpSuccess = 0;
		
		if(userData.getAccess_level().equalsIgnoreCase("level 2")) {
			try {
				 signUpSuccess = userRepository.setSignupDetails(userData.getAccess_level(), userData.getArn_number(), userData.getUsername(), 
						userData.getPass_word(), userData.getLegalName(), userData.getMgr_username());
			}catch(Exception ex) {
				
			}
		}
		
		if(userData.getAccess_level().equalsIgnoreCase("level 1")) {
			User userDetails = new User();
			userDetails.setAccessLevel(userData.getAccess_level());
			userDetails.setLegalName(userData.getLegalName());
			userDetails.setAuthorizedContact(userData.getAuthrorizedConatct());
			userDetails.setEmailAddr(userData.getEmail_addr());
			userDetails.setPhoneNumber(userData.getPhoneNumber());
			userDetails.setUsername(userData.getUsername());
			userDetails.setMgrUsername(userData.getUsername());
			userDetails.setPassWord(userData.getPass_word());
			userRepository.save(userDetails);
			message =  "Data Saved Successfully";
		}
		
		if(signUpSuccess == 1) {
			message =  "Data Saved Successfully";
		}
		
		return message;
	}

	@Override
	public String importParcel(List<FileDetails> fileData) {
		List<DataConsole> fileObjList = new ArrayList<>();
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date arrivalDate = null;
		for(FileDetails fileDataValue: fileData) {
			DataConsole fileObj = new DataConsole();
			fileObj.setAmount(BigDecimal.valueOf(fileDataValue.getAmount()));
			try {
				arrivalDate = formatter.parse(fileDataValue.getArrival_date());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fileObj.setSaleDate(arrivalDate);
			fileObj.setCurrencyCode(fileDataValue.getCurrency_code());
			fileObj.setGstEligible(fileDataValue.getGST_eligible());
			fileObj.setGstPayable(BigDecimal.valueOf(fileDataValue.getGST_payable()));
			fileObj.setReferenceNo(fileDataValue.getReference_no());
			fileObj.setReportIndicator(fileDataValue.getReport_indicator());
			fileObj.setCompanyName(fileDataValue.getCompanyName());
			fileObj.setTxnDate(new Date());
			fileObj.setUploadDate(new Date());
			fileObj.setUserCode(fileDataValue.getUser_code());
			fileObj.setUsername(fileDataValue.getUsername());
			fileObj.setFileName(fileDataValue.getFileName());
			fileObj.setAudConvertedAmount(BigDecimal.valueOf(fileDataValue.getAud_converted_value()));
			fileObjList.add(fileObj);
		}
		fileRepository.saveAll(fileObjList);
		return "File Data Imported Successfully";
	}

	@Override
	public String userCheck(String username) {
		String userSuccess = userRepository.findUserByUserName(username);
		if(userSuccess != null)
			userSuccess = "User is having the access";
		else
			userSuccess = "User does not have the access";
		return userSuccess;
	}

	@Override
	public List<Currency> currencyDetails() {
		return (List<Currency>) currencyRepositry.findAll();
	}

	@Override
	public String exportParcel(List<FileDetails> fileData) {
		List<DataConsole> fileObjList = new ArrayList<>();
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date arrivalDate = null;
		for(FileDetails fileDataValue: fileData) {
			DataConsole fileObj = new DataConsole();
			fileObj.setAmount(BigDecimal.valueOf(fileDataValue.getAmount()));
			try {
				arrivalDate = formatter.parse(fileDataValue.getArrival_date());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			fileObj.setSaleDate(arrivalDate);
			fileObj.setCurrencyCode(fileDataValue.getCurrency_code());
			fileObj.setGstEligible(fileDataValue.getGST_eligible());
			fileObj.setCompanyName(fileDataValue.getCompanyName());
			fileObj.setGstPayable(BigDecimal.valueOf(fileDataValue.getGST_payable()));
			fileObj.setReferenceNo(fileDataValue.getReference_no());
			fileObj.setReportIndicator(fileDataValue.getReport_indicator());
			fileObj.setTxnDate(new Date());
			fileObj.setUploadDate(new Date());
			fileObj.setUserCode(fileDataValue.getUser_code());
			fileObj.setUsername(fileDataValue.getUsername());
			fileObj.setFileName(fileDataValue.getFileName());
			fileObj.setAudConvertedAmount(BigDecimal.valueOf(fileDataValue.getAud_converted_value()));
			fileObjList.add(fileObj);
		}
		fileRepository.saveAll(fileObjList);
		return "File Data Exported Successfully";
	}

	@Override
	public List<DataConsole> downloadDetails(String quater, String fileType, String userCode) {
		List<DataConsole> downloadData = null;
		if(!fileType.equalsIgnoreCase("A")) {
			downloadData = fileRepository.findDataBasedOnFileType(fileType,userCode,quater);
		}else {
			downloadData = fileRepository.findDataBasedOnAll(userCode,quater);
		}
		return downloadData;
	}

	@Override
	public List<CurrencyDetails> currencyRate() {
		
		List<CurrencyDetails> currencyList = null;
		try {
		Map<String, String> countryMap = new HashMap<String, String>();
		countryMap.put("USD","United States dollar");
		countryMap.put("CNY","Chinese renminbi");
		countryMap.put("JPY","Japanese yen");
		countryMap.put("EUR","European euro");
		countryMap.put("KRW","South Korean won");
		countryMap.put("SGD","Singapore dollar");
		countryMap.put("NZD","New Zealand dollar");
		countryMap.put("GBP","UK pound sterling");
		countryMap.put("MYR","Malaysian ringgit");
		countryMap.put("THB","Thai baht");
		countryMap.put("IDR","Indonesian rupiah");
		countryMap.put("INR","Indian rupee");
		countryMap.put("TWD","New Taiwan dollar");
		countryMap.put("VND","Vietnamese dong");
		countryMap.put("HKD","Hong Kong dollar");
		countryMap.put("PGK","Papua New Guinea kina");
		countryMap.put("CHF","Swiss franc");
		countryMap.put("AED","United Arab Emirates dirham");
		countryMap.put("CAD","Canadian dollar");
		countryMap.put("AUD", "Australian Dollar");

	    URL obj = new URL("http://data.fixer.io/api/latest?access_key=7015a49ee4ba2344ba512f7026c5f7f2&base=EUR");
	    HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	    con.setRequestMethod("GET");
	   
	    int responseCode = con.getResponseCode();
		   System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == 200){
	      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	     
	     StringBuffer response = new StringBuffer();
	     String inputLine;
	      while ((inputLine = in.readLine()) != null){
	        response.append(inputLine);
	      }
	      in.close();
	      ObjectMapper mapper = new ObjectMapper();
	      CurrencyPojo currPojo = (CurrencyPojo)mapper.readValue(response.toString(), CurrencyPojo.class);
	      Double fl = ((Double)currPojo.getRates().get("AUD"));
	      currencyList = new ArrayList<CurrencyDetails>();
	      CurrencyDetails currency = null;
	      List<String> listOfCurrency = listOfCurrency();
	      for (Map.Entry<String, Double> entry : currPojo.getRates().entrySet()){
	    	if(listOfCurrency.contains(entry.getKey())) {
		    	currency = new CurrencyDetails();
		        Double flot = ((Double)entry.getValue());
		        if(entry.getKey().equalsIgnoreCase("AUD")) {
		        	currency.setAudCurrencyRate(BigDecimal.valueOf(1));
		        }else {
			        currency.setAudCurrencyRate(BigDecimal.valueOf(flot/fl).setScale(2, RoundingMode.HALF_EVEN));
		        }
		        currency.setCountry(countryMap.get(entry.getKey()));
		        currency.setCurrencyCode(entry.getKey());
		        currencyList.add(currency);
	    	}
	      }
	    }
	    else{
	      System.out.println("GET request not worked");
	    }
		}catch(Exception ex) {
			
		}
		currencyRepositry.deleteAll();
		List<Currency> currencyObjList = new ArrayList<Currency>();
		for(CurrencyDetails currencyValue: currencyList) {
			Currency currencyObj = new Currency();
			currencyObj.setAudCurrencyRate(currencyValue.getAudCurrencyRate().doubleValue());
			currencyObj.setCountry(currencyValue.getCountry());
			currencyObj.setCurrencyCode(currencyValue.getCurrencyCode());
			currencyObj.setLastUpdated(new Date());
			currencyObjList.add(currencyObj);
		}
		
		currencyRepositry.saveAll(currencyObjList);
		return currencyList;
	}
	
	private List<String> listOfCurrency() {
		List<String> profileList = new ArrayList<String>();
		profileList.add("USD");
		profileList.add("CNY");
		profileList.add("JPY");
		profileList.add("EUR");
		profileList.add("KRW");
		profileList.add("SGD");
		profileList.add("NZD");
		profileList.add("GBP");
		profileList.add("MYR");
		profileList.add("THB");
		profileList.add("IDR");
		profileList.add("INR");
		profileList.add("TWD");
		profileList.add("VND");
		profileList.add("HKD");
		profileList.add("PGK");
		profileList.add("CHF");
		profileList.add("AED");
		profileList.add("CAD");
		profileList.add("AUD");
		return profileList;
	}

	@Override
	public String accessLevel(String userName) {
		String accessLevel = null;
		accessLevel = userRepository.findAccessLevelByCode(userName);
		return accessLevel;
	}

	@Override
	public String arnRegistration(List<ArnRegistration> arnRegisterData) {
		List<User> userList = new ArrayList<User>();
		for(ArnRegistration arnRegisterVal: arnRegisterData) {
			User userObj = new User();
			userObj.setAuthorizedContact(arnRegisterVal.getAuthrorizedConatct());
			userObj.setBusinessType(arnRegisterVal.getBusinessType());
			userObj.setCountry(arnRegisterVal.getCountry());
			userObj.setEmailAddr(arnRegisterVal.getEmailAddress());
			userObj.setLegalName(arnRegisterVal.getLegalName());
			userObj.setPhoneNumber(arnRegisterVal.getPhoneNumber());
			userObj.setPostCode(arnRegisterVal.getPostCode());
			userObj.setPostalAddress(arnRegisterVal.getPostalAddress());
			userObj.setSubUrb(arnRegisterVal.getSubUrb());
			userObj.setTanNumber(arnRegisterVal.getTanNumber());
			userObj.setWebsiteName(arnRegisterVal.getWebsiteName()); 
			userObj.setDob(arnRegisterVal.getDateOfBirth());
			userObj.setRegistration_date(arnRegisterVal.getRegistrationDate());
			userObj.setCreationDate(new Date());
			userList.add(userObj);
		}
		userRepository.saveAll(userList);
		return "ARN Data Saved Successfully";
	}

	@Override
	public List<String> companyDetails() {
		List<String> userSuccess = userRepository.fetchCompanyName();
		return userSuccess;
	}

	@Override
	public OutStandingGst outStandingGst(String reportIndicator, String userCode) {
		OutStandingGst outstanding = new OutStandingGst();
		Double gstSum = fileRepository.fetchOutStandingGst(reportIndicator, userCode);
		if(gstSum != null) {
			if(reportIndicator.equalsIgnoreCase("I")) {
				outstanding.setImportGst(gstSum);
			}else if (reportIndicator.equalsIgnoreCase("E")){
				outstanding.setExportGst(gstSum);
			}
		}
		List<String> lastCurrencyUpdatedTime = currencyRepositry.latestCurrencyTimeStamp();
		String lastUpdatedCurrency = new SimpleDateFormat("yyyy-MM-dd").format(lastCurrencyUpdatedTime.get(0));
		outstanding.setLastCurrencyUpdatedTime(lastUpdatedCurrency);
		return outstanding;
	}

	@Override
	public List<String> adminDownloadDetails() {
		List<String> adminDownloadData = fileRepository.fetchAdminReport();
		return adminDownloadData;
	}

	@Override
	public String adminLogin(String userName, String passWord) {
		String loginSuccess = null;
		loginSuccess = adminRepositry.existUser(userName,passWord);
		return loginSuccess;
	}
	
}



