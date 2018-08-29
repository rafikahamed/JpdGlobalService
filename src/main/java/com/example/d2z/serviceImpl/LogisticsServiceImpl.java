package com.example.d2z.serviceImpl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d2z.dao.LogisticsDao;
import com.example.d2z.entity.Currency;
import com.example.d2z.entity.DataConsole;
import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.CurrencyDetails;
import com.example.d2z.model.FileDetails;
import com.example.d2z.model.OutStandingGst;
import com.example.d2z.model.UserDetails;
import com.example.d2z.model.UserMessage;
import com.example.d2z.repository.UserRepository;
import com.example.d2z.service.LogisticsService;

@Service
public class LogisticsServiceImpl implements LogisticsService{
	
	@Autowired
    private LogisticsDao logisticsDao;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserMessage login(String userName, String passWord) {
		String login = logisticsDao.login(userName,passWord);
		UserMessage userMsg  = new UserMessage();
		System.out.println(login);
		if(login != null) {
			String[] loginArray = login.split(",");
			userMsg.setMessage("logged In Successfully");
			userMsg.setUserName(loginArray[0]);
			userMsg.setAccess(loginArray[1]);
			userMsg.setCompanyName(loginArray[2]);
			userMsg.setUserCode(loginArray[3]);
		}else {
			userMsg.setMessage("User dose not have access to login");
			userMsg.setUserName(userName);
		}
		return userMsg;
	}

	@Override
	public UserMessage singUp(UserDetails userData) {
		String existingUser = userRepository.findUserByUserName(userData.getUsername());
		String login = null;
		UserMessage userMsg = null;
		if( existingUser == null) {
			String loginDetails = logisticsDao.singUp(userData);
			
				if(loginDetails.equalsIgnoreCase("Data Saved Successfully")) {
					login = logisticsDao.loginDetails(userData.getUsername());
					userMsg = new UserMessage();
					String[] loginArray = login.split(",");
					userMsg.setMessage("Data Saved Successfully");
					userMsg.setUserName(loginArray[0]);
					userMsg.setAccess(loginArray[1]);
					userMsg.setCompanyName(loginArray[2]);
					userMsg.setUserCode(loginArray[3]);
				}else {
					userMsg = new UserMessage();
					userMsg.setMessage(loginDetails);
					userMsg.setUserName(userData.getUsername());
				}
		}else {
			userMsg = new UserMessage();
			userMsg.setMessage("User Already Exists");
			userMsg.setUserName(userData.getUsername());
		}
		return userMsg;
	}

	@Override
	public List<FileDetails> importParcel(List<FileDetails> fileData) {
		List<FileDetails> gstCalculated = new ArrayList<FileDetails>();
		FileDetails fileDetails = null;
		String userCheck = logisticsDao.userCheck(fileData.get(0).getUsername());
		if(userCheck.equalsIgnoreCase("User is having the access")) {
			List<Currency> currencyList = logisticsDao.currencyDetails();
			 Map<String, Double> currencyMap = currencyList.stream().collect(
		                Collectors.toMap(Currency::getCurrencyCode, Currency::getAudCurrencyRate));
			// Using fileData calculate GST based on above currency List
			for(FileDetails fileValue: fileData ) {
				if(!fileValue.getGST_eligible().equalsIgnoreCase("N")) {
					String accessLevel = logisticsDao.accessLevel(fileValue.getUsername());
					if( accessLevel.equalsIgnoreCase("level 1")) {
							if( fileValue.getAccessLevel().equalsIgnoreCase("level 1")){
								List<String> userCodes = userRepository.fetchUserCodes(fileValue.getUsername());
								if( fileValue.getUser_code() != null) {
									if(userCodes.contains(fileValue.getUser_code())) {
										//Setting Calculated Value
										fileDetails = new FileDetails();
										fileDetails.setUsername(fileValue.getUsername());
										fileDetails.setUser_code(fileValue.getUser_code());
										fileDetails.setGST_eligible("Y");
										fileDetails.setReference_no(fileValue.getReference_no());
										fileDetails.setArrival_date(fileValue.getArrival_date());
										fileDetails.setCurrency_code(fileValue.getCurrency_code());
										fileDetails.setAmount(fileValue.getAmount());
										fileDetails.setReport_indicator("I");
										fileDetails.setTxn_date(fileValue.getTxn_date());
										Double audConvertedval = fileValue.getAmount() / currencyMap.get(fileValue.getCurrency_code().toUpperCase());
										if(audConvertedval < 1000 ) {
											Double gstCalculation = ( (audConvertedval) * (.10));
											fileDetails.setGST_payable(Math.floor(gstCalculation*100) / 100);
										}else {
											fileDetails.setGST_payable((double) 0);
										}
										fileDetails.setFileName(fileValue.getFileName());
										DecimalFormat formatter = new DecimalFormat(".##");
										fileDetails.setAud_converted_value(Double.parseDouble(formatter.format(audConvertedval)));
										if(!checkBetween(fileValue.getArrival_date())) {
											System.out.println("Date Check Fail");
											List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
											fileDetails = new FileDetails();
											fileDetails.setErrMessage("***Arrival date does not belong to this quarter (July 1st - September 30th)");
											gstCalculatedCheck.add(fileDetails);
											return gstCalculatedCheck;
										}
										gstCalculated.add(fileDetails);
									}else {
										List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
										fileDetails = new FileDetails();
										fileDetails.setErrMessage("***Invalid ARN Number, Please check the ARN Number in the given file");
										gstCalculatedCheck.add(fileDetails);
										return gstCalculatedCheck;
									}
								}else {
										List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
										fileDetails = new FileDetails();
										fileDetails.setErrMessage("***ARN Number should not be blank, Please check the ARN Number in the given file");
										gstCalculatedCheck.add(fileDetails);
										return gstCalculatedCheck;
								}
							}
				  }else{
						if( accessLevel.equalsIgnoreCase("level 2")){
							List<String> userCode = userRepository.fetchUserCodesByUserName(fileValue.getUsername());
							//Setting Calculated Value
							if( fileValue.getUser_code() != null) {
								if(userCode.contains(fileValue.getUser_code())) {
									fileDetails = new FileDetails();
									fileDetails.setUsername(fileValue.getUsername());
									fileDetails.setUser_code(fileValue.getUser_code());
									fileDetails.setGST_eligible("Y");
									fileDetails.setReference_no(fileValue.getReference_no());
									fileDetails.setArrival_date(fileValue.getArrival_date());
									fileDetails.setCurrency_code(fileValue.getCurrency_code());
									fileDetails.setAmount(fileValue.getAmount());
									fileDetails.setReport_indicator("I");
									fileDetails.setTxn_date(fileValue.getTxn_date());
									Double audConvertedval = fileValue.getAmount() / currencyMap.get(fileValue.getCurrency_code().toUpperCase());
									if(audConvertedval < 1000 ) {
										Double gstCalculation = ( (audConvertedval) * (.10));
										fileDetails.setGST_payable(Math.floor(gstCalculation*100) / 100);
									}else {
										fileDetails.setGST_payable((double) 0);
									}
									fileDetails.setFileName(fileValue.getFileName());
									DecimalFormat formatter = new DecimalFormat(".##");
									fileDetails.setAud_converted_value(Double.parseDouble(formatter.format(audConvertedval)));
									if(!checkBetween(fileValue.getArrival_date())) {
										List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
										System.out.println("Date Check Fail");
										fileDetails = new FileDetails();
										fileDetails.setErrMessage("***Arrival date does not belong to this quarter (July 1st - September 30th)");
										gstCalculatedCheck.add(fileDetails);
										return gstCalculatedCheck;
									}
									gstCalculated.add(fileDetails);
								}else {
									List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
									fileDetails = new FileDetails();
									fileDetails.setErrMessage("***Invalid ARN Number, Please check the ARN Number in the given file");
									gstCalculatedCheck.add(fileDetails);
									return gstCalculatedCheck;
								}
							}else {
								List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
								fileDetails = new FileDetails();
								fileDetails.setErrMessage("***ARN Number should not be blank, Please check the ARN Number in the given file");
								gstCalculatedCheck.add(fileDetails);
								return gstCalculatedCheck;
							}
						}else {
							//Invalid user code
							List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
							fileDetails = new FileDetails();
							fileDetails.setErrMessage("***Invalid ARN Number, Please check the ARN Number in the given file");
							gstCalculatedCheck.add(fileDetails);
							return gstCalculatedCheck;
						}
					}
				}else {
					System.out.println("N --->");
				}
			}
			logisticsDao.importParcel(gstCalculated);
		}else {
			
		}
		return gstCalculated;
	}

	@Override
	public List<Currency> currencyDetails() {
		List<Currency> currencyList = logisticsDao.currencyDetails();
		return currencyList;
	}

	@Override
	public List<FileDetails> exportParcel(List<FileDetails> fileData) {
		 List<FileDetails> gstCalculated = new ArrayList<FileDetails>();
		 FileDetails fileDetails = null;
		String userCheck = logisticsDao.userCheck(fileData.get(0).getUsername());
		if(userCheck.equalsIgnoreCase("User is having the access")) {
			List<Currency> currencyList = logisticsDao.currencyDetails();
			 Map<String, Double> currencyMap = currencyList.stream().collect(
		                Collectors.toMap(Currency::getCurrencyCode, Currency::getAudCurrencyRate));
			//Using fileData calculate GST based on above currency List
				for(FileDetails fileValue: fileData ) {
					if(!fileValue.getGST_eligible().equalsIgnoreCase("N")) {
						String accessLevel = logisticsDao.accessLevel(fileValue.getUsername());
						if( accessLevel.equalsIgnoreCase("level 1")) {
								if( fileValue.getAccessLevel().equalsIgnoreCase("level 1")){
									List<String> userCodes = userRepository.fetchUserCodes(fileValue.getUsername());
									if( fileValue.getUser_code() != null) {
										if(userCodes.contains(fileValue.getUser_code())) {
											//Setting Calculated Value
											fileDetails = new FileDetails();
											fileDetails.setUsername(fileValue.getUsername());
											fileDetails.setUser_code(fileValue.getUser_code());
											fileDetails.setGST_eligible("Y");
											fileDetails.setReference_no(fileValue.getReference_no());
											fileDetails.setArrival_date(fileValue.getArrival_date());
											fileDetails.setCurrency_code(fileValue.getCurrency_code());
											fileDetails.setAmount(fileValue.getAmount());
											fileDetails.setReport_indicator("E");
											fileDetails.setTxn_date(fileValue.getTxn_date());
											Double audConvertedval = fileValue.getAmount() / currencyMap.get(fileValue.getCurrency_code().toUpperCase());
											if(audConvertedval < 1000 ) {
												Double gstCalculation = ( -(audConvertedval) * (.10));
												fileDetails.setGST_payable(Math.floor(gstCalculation*100) / 100);
											}else {
												fileDetails.setGST_payable((double) 0);
											}
											fileDetails.setFileName(fileValue.getFileName());
											DecimalFormat formatter = new DecimalFormat(".##");
											fileDetails.setAud_converted_value(Double.parseDouble(formatter.format(audConvertedval)));
											if(!checkBetween(fileValue.getArrival_date())) {
												System.out.println("Date Check Fail");
												List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
												fileDetails = new FileDetails();
												fileDetails.setErrMessage("***Arrival date does not belong to this quarter (July 1st - September 30th)");
												gstCalculatedCheck.add(fileDetails);
												return gstCalculatedCheck;
											}
											gstCalculated.add(fileDetails);
										}else {
											List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
											fileDetails = new FileDetails();
											fileDetails.setErrMessage("***Invalid ARN Number, Please check the ARN Number in the given file");
											gstCalculatedCheck.add(fileDetails);
											return gstCalculatedCheck;
										}
									}else {
											List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
											fileDetails = new FileDetails();
											fileDetails.setErrMessage("***ARN Number should not be blank, Please check the ARN Number in the given file");
											gstCalculatedCheck.add(fileDetails);
											return gstCalculatedCheck;
									}
								}
					  }else{
							if( accessLevel.equalsIgnoreCase("level 2")){
								List<String> userCode = userRepository.fetchUserCodesByUserName(fileValue.getUsername());
								//Setting Calculated Value
								if( fileValue.getUser_code() != null) {
									if(userCode.contains(fileValue.getUser_code())) {
										fileDetails = new FileDetails();
										fileDetails.setUsername(fileValue.getUsername());
										fileDetails.setUser_code(fileValue.getUser_code());
										fileDetails.setGST_eligible("Y");
										fileDetails.setReference_no(fileValue.getReference_no());
										fileDetails.setArrival_date(fileValue.getArrival_date());
										fileDetails.setCurrency_code(fileValue.getCurrency_code());
										fileDetails.setAmount(fileValue.getAmount());
										fileDetails.setReport_indicator("E");
										fileDetails.setTxn_date(fileValue.getTxn_date());
										Double audConvertedval = fileValue.getAmount() / currencyMap.get(fileValue.getCurrency_code().toUpperCase());
										if(audConvertedval < 1000 ) {
											Double gstCalculation = ( -(audConvertedval) * (.10));
											fileDetails.setGST_payable(Math.floor(gstCalculation*100) / 100);
										}else {
											fileDetails.setGST_payable((double) 0);
										}
										fileDetails.setFileName(fileValue.getFileName());
										DecimalFormat formatter = new DecimalFormat(".##");
										fileDetails.setAud_converted_value(Double.parseDouble(formatter.format(audConvertedval)));
										if(!checkBetween(fileValue.getArrival_date())) {
											List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
											System.out.println("Date Check Fail");
											fileDetails = new FileDetails();
											fileDetails.setErrMessage("***Arrival date does not belong to this quarter (July 1st - September 30th)");
											gstCalculatedCheck.add(fileDetails);
											return gstCalculatedCheck;
										}
										gstCalculated.add(fileDetails);
									}else {
										List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
										fileDetails = new FileDetails();
										fileDetails.setErrMessage("***Invalid ARN Number, Please check the ARN Number in the given file");
										gstCalculatedCheck.add(fileDetails);
										return gstCalculatedCheck;
									}
								}else {
									List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
									fileDetails = new FileDetails();
									fileDetails.setErrMessage("***ARN Number should not be blank, Please check the ARN Number in the given file");
									gstCalculatedCheck.add(fileDetails);
									return gstCalculatedCheck;
								}
							}else {
								List<FileDetails> gstCalculatedCheck = new ArrayList<FileDetails>();
								fileDetails = new FileDetails();
								fileDetails.setErrMessage("***Invalid ARN Number, Please check the ARN Number in the given file");
								gstCalculatedCheck.add(fileDetails);
								return gstCalculatedCheck;
							}
						}
					}else {
						System.out.println("N --->");
					}
				}
			logisticsDao.exportParcel(gstCalculated);
		}else {
			
		}
		return gstCalculated;
	}

	@Override
	public List<DataConsole> downloadDetails(String quater, String fileType, String userCode) {
		List<DataConsole> currencyList = logisticsDao.downloadDetails(quater,fileType, userCode);
		return currencyList;
	}

	@Override
	public List<CurrencyDetails> currencyRate() {
		// TODO Auto-generated method stub
		List<CurrencyDetails> currencyList = logisticsDao.currencyRate();
		return currencyList;
	}

	@Override
	public String accessLevel(String companyName, String userCode) {
		String accessLevel = logisticsDao.accessLevel(companyName);
		return accessLevel;
	}
	
	//public static boolean checkBetween(String dateToCheck, String startDate, String endDate) {
	public static boolean checkBetween(String dateToCheck) {
	    boolean res = false;
	    String startDate = "01-JUL-2018";
	    String endDate = "30-SEP-2018";
	    SimpleDateFormat fmt1 = new SimpleDateFormat("dd-MMM-yyyy"); //22-05-2013
	    SimpleDateFormat fmt2 = new SimpleDateFormat("dd-MMM-yyyy"); //22-05-2013
	    try {
	     Date requestDate = fmt2.parse(dateToCheck);
	     Date fromDate = fmt1.parse(startDate);
	     Date toDate = fmt1.parse(endDate);
	     res = requestDate.compareTo(fromDate) >= 0 && requestDate.compareTo(toDate) <=0;
	    }catch(ParseException pex){
	        pex.printStackTrace();
	    }
	    return res;
	 }

	@Override
	public List<ArnRegistration> arnRegistration(List<ArnRegistration> arnRegisterData) {
		String arnData = logisticsDao.arnRegistration(arnRegisterData);
		if(arnData.equalsIgnoreCase("ARN Data Saved Successfully")) {
			arnRegisterData.get(0).setMessage(arnData);
		}else {
			arnRegisterData.get(0).setMessage("Unable to save the ARN Data");
		}
		return arnRegisterData;
	}

	@Override
	public List<String> companyDetails() {
		List<String> companyList = logisticsDao.companyDetails();
		return companyList;
	}

	@Override
	public OutStandingGst outStandingGst(String reportIndicator, String userCode) {
		OutStandingGst gstDetails = logisticsDao.outStandingGst(reportIndicator, userCode);
		return gstDetails;
	}

}
