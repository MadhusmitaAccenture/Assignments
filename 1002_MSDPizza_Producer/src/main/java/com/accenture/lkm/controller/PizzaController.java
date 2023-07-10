package com.accenture.lkm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.dto.PizzaCustomerContactNumberDto;
import com.accenture.lkm.dto.PizzaNameDto;
import com.accenture.lkm.dto.PizzaOrderDto;
import com.accenture.lkm.service.PizzaService;

@RestController
@RequestMapping("pizzaorder")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	/**
	 * Add Pizza Order detail API
	 * 
	 * @param pizzaOrderDTO
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> addPizza(@RequestBody PizzaOrderDto pizzaOrderDTO) {
		int id = pizzaService.addPizza(pizzaOrderDTO);
		String responseStr = "Pizza Order Placed Successfully: " + id;
		return new ResponseEntity<String>(responseStr, HttpStatus.CREATED);
	}

	/**
	 * Get list of PizzaOrder details by Pizza Name
	 * 
	 * @param pizzaNameDto
	 * @return
	 */
	@PostMapping(value = "/pizzaName")
	public ResponseEntity<List<PizzaOrderDto>> getallDetailsByPizaaName(@RequestBody PizzaNameDto pizzaNameDto) {
		if (pizzaNameDto != null && !pizzaNameDto.getPizzaName().isEmpty()) {
			List<PizzaOrderDto> pizzaDetailList = pizzaService.getallDetailsByPizaaName(pizzaNameDto.getPizzaName());
			if (!pizzaDetailList.isEmpty()) {
				return new ResponseEntity<List<PizzaOrderDto>>(pizzaDetailList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Find All PizzaOrder details
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<PizzaOrderDto>> getPizzaDetails() {
		List<PizzaOrderDto> pizzaDetailList = pizzaService.getallDetails();
		if (!pizzaDetailList.isEmpty()) {
			return new ResponseEntity<List<PizzaOrderDto>>(pizzaDetailList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.NOT_FOUND);
		}
	}

	// getOrderDetailsByContactNumber(String customerContactNumber)
	/**
	 * Get list of PizzaOrder using ContactNumber
	 * 
	 * @param pizzaCustContactNumberDto
	 * @return
	 */
	@PostMapping(value = "/customerContactNumber")
	public ResponseEntity<List<PizzaOrderDto>> getOrderDetailsByContactNumber(
			@RequestBody PizzaCustomerContactNumberDto pizzaCustContactNumberDto) {
		if (pizzaCustContactNumberDto != null && !pizzaCustContactNumberDto.getCustomerContactNumber().isEmpty()) {
			List<PizzaOrderDto> pizzaDetailList = pizzaService
					.getOrderDetailsByContactNumber(pizzaCustContactNumberDto.getCustomerContactNumber());
			if (!pizzaDetailList.isEmpty()) {
				return new ResponseEntity<List<PizzaOrderDto>>(pizzaDetailList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.NOT_FOUND);
		}
	}
}
