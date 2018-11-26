package com.example.d2z.validator;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.d2z.model.ArnRegistration;
import com.example.d2z.model.FileDetails;
import com.example.d2z.repository.FileDetailsRepository;
import com.example.d2z.repository.UserRepository;

@Service
public class Validator {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	FileDetailsRepository fileRepository;
	
	public boolean isCompanyNameUnique(List<ArnRegistration> arnRegisterData){
		boolean isValid = false;
		List<String> companyName_DB = userRepository.fetchAllCompanyName();
		
		List<String> incomingCompanyName = arnRegisterData.stream().map(obj -> {
			return obj.getLegalName(); })
				.collect(Collectors.toList());
		
		companyName_DB.addAll(incomingCompanyName);
		 
		List<String> duplicateCompanyNm= companyName_DB.stream().collect(Collectors.groupingBy(Function.identity(),     
	              Collectors.counting()))                                             
	          .entrySet().stream()
	          .filter(e -> e.getValue() > 1)                                      
	          .map(e -> e.getKey())                                                  
	          .collect(Collectors.toList());
		
		if(duplicateCompanyNm.isEmpty()) {
			isValid = true;
		}		
		return isValid;
	}

	public boolean isReferenceNumberUnique(List<FileDetails> fileData) {
		boolean isValid = false;
		List<String> referenceNumber_DB = fileRepository.fetchAllReferenceNumbers();
		
		List<String> incomingRefNbr = fileData.stream().map(obj -> {
			return obj.getReference_no(); })
				.collect(Collectors.toList());
		
		referenceNumber_DB.addAll(incomingRefNbr);
		 
		List<String> duplicateRefNbr= referenceNumber_DB.stream().collect(Collectors.groupingBy(Function.identity(),     
	              Collectors.counting()))                                             
	          .entrySet().stream()
	          .filter(e -> e.getValue() > 1)                                      
	          .map(e -> e.getKey())                                                  
	          .collect(Collectors.toList());
		System.out.println(duplicateRefNbr);
		if(duplicateRefNbr.isEmpty()) {
			isValid = true;
		}		
		return isValid;
	}


}
