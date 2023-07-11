package com.accenture.lkm.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.lkm.model.PizzaCustomerContactNumberDto;
import com.accenture.lkm.model.PizzaNameDto;
import com.accenture.lkm.model.PizzaOrderDto;

@FeignClient(name = "cst-msdpizza-producer")
public interface MyFeignClient {

	@GetMapping(value = "pizzaorder")
	public ResponseEntity<List<PizzaOrderDto>> getPizzaDetails();

	@PostMapping(value = "pizzaorder")
	public ResponseEntity<String> addPizza(@RequestBody PizzaOrderDto pizzaOrderDTO);

	@PostMapping(value = "pizzaorder/pizzaName")
	public ResponseEntity<List<PizzaOrderDto>> getAllDetailsByPizaaName(@RequestBody PizzaNameDto pizzaNameDto);
	
	@PostMapping(value = "pizzaorder/customerContactNumber")
	public ResponseEntity<List<PizzaOrderDto>> getOrderDetailsByContactNumber(
			@RequestBody PizzaCustomerContactNumberDto pizzaCustContactNumberDto);

}
