package com.accenture.lkm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.lkm.model.PizzaOrderEntity;

public interface PizzaDao extends JpaRepository<PizzaOrderEntity, Integer> {
	
	/**
	 * Find list of PizzaOrder by PizzaName
	 * 
	 * @param pizzaName
	 * @return
	 */
	List<PizzaOrderEntity> findByPizzaName(String pizzaName);
	
	/**
	 * Find list of PizzaOrder using Contact Number
	 * 
	 * @param customerContactNumber
	 * @return
	 */
	List<PizzaOrderEntity> findByCustomerContactNumber(String customerContactNumber);
	
	/**
	 * Get list of PizzaOrder by customer contact number
	 * 
	 * @param number
	 * @return
	 */
	@Query(name="select p from PizzaOrderEntity p where p.customerContactNumber=:number") 
	List<PizzaOrderEntity> getListByCustomerContactNumber(String number);
}
