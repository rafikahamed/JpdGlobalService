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

}
