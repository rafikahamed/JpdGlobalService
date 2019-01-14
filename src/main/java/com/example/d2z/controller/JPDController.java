package com.example.d2z.controller;

import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.d2z.entity.Currency;
import com.example.d2z.entity.DataConsole;
import com.example.d2z.model.AdminDownloadModel;
import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.CurrencyDetails;
import com.example.d2z.model.DropDown;
import com.example.d2z.model.FileDetails;
import com.example.d2z.model.OutStandingGst;
import com.example.d2z.model.UserDetails;
import com.example.d2z.model.UserMessage;
import com.example.d2z.service.LogisticsService;

@RestController
@Validated
@RequestMapping(value = "/v1/logistics")
public class JPDController {
	
	@Autowired
    private LogisticsService logisticsService;
	
	@RequestMapping( method = RequestMethod.GET, path = "/login")
    public UserMessage login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
		UserMessage loginMsg = logisticsService.login(userName,passWord);
		return loginMsg;
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/adminLogin")
    public UserMessage adminLogin(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
		UserMessage loginMsg = logisticsService.adminLogin(userName,passWord);
		return loginMsg;
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/contactUs")
    public String contactUs(@RequestParam("email") String email, @RequestParam("message") String message, @RequestParam("name") String name,
    			@RequestParam("subject") String subject) {
		String loginMsg = logisticsService.contactUs(email, message, name, subject);
		return loginMsg;
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/accessLevel")
    public String accessLevel(@RequestParam("companyName") String companyName, @RequestParam("userCode") String userCode) {
		String accessLevel = logisticsService.accessLevel(companyName,userCode);
		return accessLevel;
    }
    
	@RequestMapping( method = RequestMethod.POST, path = "/signup", consumes=MediaType.APPLICATION_JSON)
	public UserMessage singUp(@Valid @RequestBody UserDetails userData) {
		return logisticsService.singUp(userData);
    }
	
	@RequestMapping( method = RequestMethod.POST, path = "/arnRegistration", consumes=MediaType.APPLICATION_JSON)
	public List<ArnRegistration> arnRegistration(@RequestBody List<@Valid ArnRegistration> arnRegisterData)  {
		return logisticsService.arnRegistration(arnRegisterData);
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/currency")
	public List<Currency> currencyDetails() {
		return logisticsService.currencyDetails();
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/companyDetails")
	public List<DropDown> companyDetails() {
		return logisticsService.companyDetails();
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/fetchMgrUserName")
	public List<DropDown> fetchMgrUserName() {
		return logisticsService.fetchMgrUserName();
    }
	
	@RequestMapping( method = RequestMethod.GET, path = "/fileName")
	public List<DropDown> fileNames(@RequestParam("userName") String userName) {
		return logisticsService.fileNames(userName);
    }
    
    @RequestMapping( method = RequestMethod.POST, path = "/import", consumes= MediaType.APPLICATION_JSON)
    public List<FileDetails> importParcel(@RequestBody List<FileDetails> fileData) {
		return logisticsService.importParcel(fileData);
    }
    
    @RequestMapping( method = RequestMethod.POST, path = "/export", consumes= MediaType.APPLICATION_JSON)
    public List<FileDetails> exportParcel(@RequestBody List<FileDetails> fileData) {
		return logisticsService.exportParcel(fileData);
    }
    
    @RequestMapping( method = RequestMethod.GET, path = "/importExportDetails", produces= MediaType.APPLICATION_JSON)
    public List<DataConsole> importExportDetails(@RequestParam("fileName") String fileName) {
    	List<DataConsole> dataConsoleDetails = logisticsService.importExportDetails(fileName);
    	return dataConsoleDetails;
    }
    
    @RequestMapping( method = RequestMethod.GET, path = "/deleteGstEntry")
    public UserMessage deleteGstData(@RequestParam("reference_no") String reference_no) {
		UserMessage deleteGstData = logisticsService.deleteGstData(reference_no);
		return deleteGstData;
    }
    
    @RequestMapping( method = RequestMethod.GET, path = "/individualDownload", produces= MediaType.APPLICATION_JSON)
    public List<DataConsole> downloadDetails(@RequestParam("quater") String quater, @RequestParam("fileType") String fileType, @RequestParam("userCode") String userCode) {
    	List<DataConsole> dataConsoleDetails = logisticsService.downloadDetails(quater,fileType,userCode);
		return dataConsoleDetails;
    }
    
    @RequestMapping( method = RequestMethod.GET, path = "/outStandingGst", produces= MediaType.APPLICATION_JSON)
    public OutStandingGst outStandingGst(@RequestParam("reportIndicator") String reportIndicator, @RequestParam("userCode") String userCode) {
    	OutStandingGst dataConsoleDetails = logisticsService.outStandingGst(reportIndicator,userCode);
		return dataConsoleDetails;
    }
    
    @RequestMapping( method = RequestMethod.GET, path = "/currencyRate", produces= MediaType.APPLICATION_JSON)
    public List<CurrencyDetails> currencyRate() {
    	List<CurrencyDetails> dataConsoleDetails = logisticsService.currencyRate();
		return dataConsoleDetails;
    }
    
    @RequestMapping( method = RequestMethod.GET, path = "/adminDownload", produces= MediaType.APPLICATION_JSON )
    public List<AdminDownloadModel> adminDownloadDetails() {
    	List<AdminDownloadModel> dataConsoleDetails = logisticsService.adminDownloadDetails();
		return dataConsoleDetails;
    }
}
