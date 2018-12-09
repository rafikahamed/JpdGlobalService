package com.example.d2z.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.d2z.entity.DataConsole;

//This will be AUTO IMPLEMENTED by Spring into a Bean called FileDetailsRepository
//CRUD refers Create, Read, Update, Delete

public interface FileDetailsRepository extends CrudRepository<DataConsole, Long>{
	
	 @Query("SELECT t FROM DataConsole t where datename(qq,sale_date) = :quater and  t.reportIndicator = :fileType and t.userCode = :userCode and t.isdeleted is null") 
	 List<DataConsole> findDataBasedOnFileType( @Param("fileType") String fileType, @Param("userCode") String userCode, @Param("quater") String quater);
	 
	 @Query(value="SELECT t FROM DataConsole t where datename(qq,sale_date) = :quater and t.userCode = :userCode and t.isdeleted is null") 
	 List<DataConsole> findDataBasedOnAll( @Param("userCode") String userCode, @Param("quater") String quater);
	 
	 @Query("SELECT sum(t.gstPayable) FROM DataConsole t WHERE t.reportIndicator=:reportIndicator and t.userCode =:userCode") 
	 Double fetchOutStandingGst(@Param("reportIndicator") String reportIndicator, @Param("userCode") String userCode);
	 
	 @Query(nativeQuery = true, value ="SELECT a.user_code, \n" + 
	 		"       c.company_name, \n" + 
	 		"       a.gst_payable, \n" + 
	 		"       a.total_sales_in_aud, \n" + 
	 		"       b.total_gst_exclude_sales_in_aud \n" + 
	 		"FROM   (SELECT user_code, \n" + 
	 		"               NULL                       AS company_name, \n" + 
	 		"               Sum (gst_payable)          AS GST_payable, \n" + 
	 		"               Sum (aud_converted_amount) AS Total_sales_in_AUD, \n" + 
	 		"               NULL                       AS gst_exclude \n" + 
	 		"        FROM   data_console \n" + 
	 		"		where isdeleted is null\n" + 
	 		"        GROUP  BY user_code) a \n" + 
	 		"       INNER JOIN (SELECT user_code, \n" + 
	 		"                          NULL                      AS company_name, \n" + 
	 		"                          NULL                      AS GST_payable, \n" + 
	 		"                          NULL                      AS amount, \n" + 
	 		"                          Sum(aud_converted_amount) AS \n" + 
	 		"                          Total_GST_Exclude_sales_in_AUD \n" + 
	 		"                   FROM   data_console \n" + 
	 		"                   WHERE  (gst_payable = '0.00'\n" + 
	 		"				     OR gst_payable IS NULL )\n" + 
	 		"					 and isdeleted is null\n" + 
	 		"                   GROUP  BY user_code \n" + 
	 		"                   UNION \n" + 
	 		"                   SELECT user_code, \n" + 
	 		"                          NULL AS company_name, \n" + 
	 		"                          NULL AS GST_payable, \n" + 
	 		"                          NULL AS amount, \n" + 
	 		"                          NULL AS Total_GST_Exclude_sales_in_AUD \n" + 
	 		"                   FROM   data_console \n" + 
	 		"                   WHERE  company_name IN (SELECT DISTINCT legal_name \n" + 
	 		"                                           FROM   users) \n" + 
	 		"                          AND company_name NOT IN (SELECT DISTINCT company_name \n" + 
	 		"                                                   FROM   data_console \n" + 
	 		"                                                   WHERE  (gst_payable = '0.00' \n" + 
	 		"                                                           OR gst_payable IS \n" + 
	 		"                                                              NULL) \n" + 
	 		"															   and isdeleted is null) \n" + 
	 		"                   GROUP  BY user_code) b \n" + 
	 		"               ON a.user_code = b.user_code \n" + 
	 		"       INNER JOIN (SELECT user_code, \n" + 
	 		"                          legal_name AS company_name, \n" + 
	 		"                          NULL       AS GST_payable, \n" + 
	 		"                          NULL       AS Total_sales_in_AUD, \n" + 
	 		"                          NULL       AS Total_GST_Exclude_sales_in_AUD \n" + 
	 		"                   FROM   users) c \n" + 
	 		"               ON a.user_code = c.user_code") 
	 List<String> fetchAdminReport();

	@Query("SELECT t.referenceNo FROM DataConsole t")
	List<String> fetchAllReferenceNumbers();
	
	@Query("SELECT distinct(t.fileName) from DataConsole t where t.isdeleted is null and t.username=:userName")
	List<String> fetchFileDetails(@Param("userName") String userName );
	
	@Query("select t from DataConsole t  where t.fileName = :fileName and t.isdeleted is null")
	List<DataConsole> fetchImportExportDetails(@Param("fileName") String fileName);
	
	@Procedure(name = "delete_gst_data")
	void deleteGstData( @Param("reference_no") String reference_no);
	 
}
