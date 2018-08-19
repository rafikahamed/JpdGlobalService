package com.example.d2z.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.d2z.entity.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
	
	 @Query("SELECT t.lastUpdated FROM Currency t ORDER BY Date(t.lastUpdated) ASC") 
	 List<String> latestCurrencyTimeStamp();

}
