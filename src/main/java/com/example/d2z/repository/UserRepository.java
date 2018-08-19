package com.example.d2z.repository;

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
	
	 @Query("SELECT t.username, t.access_level, t.legal_name, t.userCode FROM User t where t.username = :userName and t.pass_word = :passWord" ) 
	 String findUserByUserCredentails(@Param("userName") String userName, @Param("passWord") String passWord);
	 
	 @Query("SELECT t.username, t.access_level, t.legal_name, t.userCode FROM User t where t.username = :userName" ) 
	 String findUserByUserName(@Param("userName") String userName);
	 
	 @Query("SELECT t.access_level FROM User t where t.username = :userName" ) 
	 String findAccessLevelByCode(@Param("userName") String userName);
	 
	 @Transactional
	 @Modifying
	 @Query("update User u set u.access_level = :level, u.userCode= :arnNumber, u.username = :userName, u.pass_word = :passWord, u.mgr_username = :ManagerName where u.legal_name = :legalName")
	 int setSignupDetails(@Param("level") String level, @Param("arnNumber") String arnNumber, @Param("userName") String userName,
			 								@Param("passWord") String passWord, @Param("legalName") String legalName, @Param("ManagerName") String ManagerName);
	 
	 @Query("SELECT t.legal_name FROM User t") 
	 List<String> fetchCompanyName();
	 
	 @Query("SELECT t.userCode FROM User t where t.mgr_username = :managerName") 
	 List<String> fetchUserCodes(@Param("managerName") String managerName);
	 
	 @Query("SELECT t.userCode FROM User t where t.username = :userName") 
	 List<String> fetchUserCodesByUserName(@Param("userName") String userName);
	 
}
