package com.example.d2z.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.d2z.entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
	
	 @Query("SELECT t.username, t.accessLevel, t.legalName, t.userCode FROM User t where t.username = :userName and t.passWord = :passWord" ) 
	 String findUserByUserCredentails(@Param("userName") String userName, @Param("passWord") String passWord);
	 
	 @Query("SELECT t.username, t.accessLevel, t.legalName, t.userCode FROM User t where t.username = :userName" ) 
	 String findUserByUserName(@Param("userName") String userName);
	 
	 @Query("SELECT t.accessLevel FROM User t where t.username = :userName" ) 
	 String findAccessLevelByCode(@Param("userName") String userName);
	 
	 @Transactional
	 @Modifying
	 @Query("update User u set u.accessLevel = :level, u.userCode= :arnNumber, u.username = :userName, u.passWord = :passWord, u.mgrUsername = :ManagerName, "
	 		+ "u.timestamp = :updatedTime where u.legalName = :legalName")
	 int setSignupDetails(@Param("level") String level, @Param("arnNumber") String arnNumber, @Param("userName") String userName,
			 								@Param("passWord") String passWord, @Param("legalName") String legalName, 
			 								@Param("ManagerName") String ManagerName, @Param("updatedTime") Timestamp updatedTime);
	 
	 @Query("SELECT distinct t.legalName FROM User t where t.username is not null") 
	 List<String> fetchCompanyName();
	 
	 @Query("SELECT t.userCode FROM User t where t.mgrUsername = :managerName") 
	 List<String> fetchUserCodes(@Param("managerName") String managerName);
	 
	 @Query("SELECT t.userCode FROM User t where t.username = :userName") 
	 List<String> fetchUserCodesByUserName(@Param("userName") String userName);
	 
	 @Query("SELECT distinct t.legalName FROM User t WHERE t.legalName is not null and t.legalName != '' ") 
	 List<String> fetchAllCompanyName();

	 @Query("SELECT distinct t.legalName FROM User t where t.legalName = :companyName") 
	 String findUserByCompanyName(@Param("companyName") String companyName);
	 
	 @Query("SELECT t.userCode FROM User t where t.userCode = :arnNumber") 
	 String findUserByARNNumber(@Param("arnNumber") String arn_number);

	 @Query("SELECT distinct(t.username) FROM User t where accessLevel='level 1' ") 
	 List<String> fetchManagerUserName();
	 
}
