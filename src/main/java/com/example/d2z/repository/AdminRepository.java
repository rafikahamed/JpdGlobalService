package com.example.d2z.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.d2z.entity.Admindata;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AdminRepository extends CrudRepository<Admindata, Long> {
	
	 @Query("SELECT a.username FROM Admindata a where a.username = :userName and a.password = :passWord" ) 
	 String existUser(@Param("userName") String userName, @Param("passWord") String passWord);
	 
}
