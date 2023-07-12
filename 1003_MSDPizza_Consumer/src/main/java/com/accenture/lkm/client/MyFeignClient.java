package com.accenture.lkm.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.lkm.model.PizzaCustomerContactNumberDto;
import com.accenture.lkm.model.PizzaNameDto;
import com.accenture.lkm.model.PizzaOrderDto;

@FeignClient(name = "msd-pizza-producer")
public interface MyFeignClient {

	@GetMapping(value = "api/v1/msd/pizzaorder")
	public ResponseEntity<List<PizzaOrderDto>> getPizzaDetails();

	@PostMapping(value = "api/v1/msd/pizzaorder")
	public ResponseEntity<String> addPizza(@Valid @RequestBody PizzaOrderDto pizzaOrderDTO);

	@PostMapping(value = "api/v1/msd/pizzaorder/pizzaName")
	public ResponseEntity<List<PizzaOrderDto>> getAllDetailsByPizzaName(@Valid @RequestBody PizzaNameDto pizzaNameDto);
	
	@PostMapping(value = "api/v1/msd/pizzaorder/customerContactNumber")
	public ResponseEntity<List<PizzaOrderDto>> getOrderDetailsByContactNumber(
			@Valid @RequestBody PizzaCustomerContactNumberDto pizzaCustContactNumberDto);

}
