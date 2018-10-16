package com.example.d2z.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.d2z.entity.DataConsole;

//This will be AUTO IMPLEMENTED by Spring into a Bean called FileDetailsRepository
//CRUD refers Create, Read, Update, Delete

public interface FileDetailsRepository extends CrudRepository<DataConsole, Long>{
	
	 @Query("SELECT t FROM DataConsole t where t.reportIndicator = :fileType and t.userCode = :userCode") 
	 List<DataConsole> findDataBasedOnFileType( @Param("fileType") String fileType, @Param("userCode") String userCode);
	 
	 @Query("SELECT t FROM DataConsole t where t.userCode = :userCode") 
	 List<DataConsole> findDataBasedOnAll( @Param("userCode") String userCode);
	 
	 @Query("SELECT sum(t.gstPayable) FROM DataConsole t WHERE t.reportIndicator=:reportIndicator and t.userCode =:userCode") 
	 Double fetchOutStandingGst(@Param("reportIndicator") String reportIndicator, @Param("userCode") String userCode);
	 
//	 @Query(nativeQuery = true, value ="Select a.user_code,c.company_name,a.gst_payable,a.Total_sales_in_AUD,b.Total_GST_Exclude_sales_in_AUD\n" + 
//	 		"from \n" + 
//	 		"(SELECT user_code,null  as company_name,SUM (GST_payable) as GST_payable, SUM (aud_converted_amount) as Total_sales_in_AUD,null as gst_exclude\n" + 
//	 		"FROM data_console \n" + 
//	 		"GROUP BY user_code) a \n" + 
//	 		"inner join\n" + 
//	 		"(select user_code,null as company_name,null as GST_payable, null as amount,sum(aud_converted_amount) as Total_GST_Exclude_sales_in_AUD\n" + 
//	 		"from data_console \n" + 
//	 		"where gst_payable='0.00'\n" + 
//	 		"GROUP BY user_code) b\n" + 
//	 		"on a.user_code = b.user_code\n" + 
//	 		"inner join\n" + 
//	 		"(select user_code,legal_name as company_name,null as GST_payable, null as Total_sales_in_AUD,null as Total_GST_Exclude_sales_in_AUD\n" + 
//	 		"from users) c\n" + 
//	 		"on a.user_code = c.user_code") 
//	 List<String> fetchAdminReport();
	 
	 @Query(nativeQuery = true, value ="Select a.user_code,c.company_name,a.gst_payable,a.Total_sales_in_AUD,b.Total_GST_Exclude_sales_in_AUD from\n" + 
	 					"(SELECT user_code,null  as company_name,SUM (GST_payable) as GST_payable, SUM (aud_converted_amount) as Total_sales_in_AUD,null as gst_exclude\n" + 
	 					"FROM data_console  GROUP BY user_code) a inner join\n" + 
	 					"(select user_code,null as company_name,null as GST_payable, null as amount,sum(aud_converted_amount) as Total_GST_Exclude_sales_in_AUD\n" + 
	 					"from data_console\n" + 
	 					"where gst_payable='0.00' or GST_payable is null\n" + 
	 					"GROUP BY user_code\n" + 
	 					"Union\n" + 
	 					"select user_code,null as company_name,null as GST_payable, null as amount, null as Total_GST_Exclude_sales_in_AUD\n" + 
	 					"from data_console\n" + 
	 					"where company_name in (select distinct Legal_name from users)\n" + 
	 					"and company_name not in (select distinct company_name\n" + 
	 					"from data_console\n" + 
	 					"where gst_payable='0.00' or GST_payable is null )\n" + 
	 					"GROUP BY user_code) b\n" + 
	 					"on a.user_code = b.user_code\n" + 
	 					"inner join\n" + 
	 					"(select user_code,legal_name as company_name,null as GST_payable, null as Total_sales_in_AUD,null as Total_GST_Exclude_sales_in_AUD\n" + 
	 					"from users) c\n" + 
	 					"on a.user_code = c.user_code") 
	 List<String> fetchAdminReport();
	 
}
