package com.accenture.lkm.client;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.model.PizzaCustomerContactNumberDto;
import com.accenture.lkm.model.PizzaNameDto;
import com.accenture.lkm.model.PizzaOrderDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
@RequestMapping("api/v1/msd/pizzaorder")
public class ConsumerControllerClient {

	@Autowired
	private MyFeignClient feignClient;

	// Fetch data from config server
	@Value("${SimpleValue:Hello default 1}")
	private String message1;

	@Value("${cst_property2:Hello default 2}")
	private String message2;

	@Value("${fallback_message:Connection Error! }")
	private String fallbackMsg;

	public static Logger logger = Logger.getLogger(ConsumerControllerClient.class);

	/**
	 * Test fetching data from config server
	 * 
	 * @return
	 */
	@RequestMapping("/getMessage")
	public String getMessage() {
		return this.message1 + ", " + message2;
	}

	/**
	 * Fetch All Pizza-Order details
	 * 
	 * @return
	 */
	@GetMapping
	@HystrixCommand(fallbackMethod = "getFallBackForGetPizzaDetails", commandKey = "msdKeyCompute")
	public ResponseEntity<List<PizzaOrderDto>> getPizzaDetails() {
		return feignClient.getPizzaDetails();
	}

	/**
	 * Fallback method for getPizzaDetails
	 * 
	 * @return
	 */
	public ResponseEntity<List<PizzaOrderDto>> getFallBackForGetPizzaDetails() {
		logger.warn(fallbackMsg);
		return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.EXPECTATION_FAILED);
	}

	/**
	 * Fallback method for addPizza
	 * 
	 * @param pizzaOrderDTO
	 * @return
	 */
	@PostMapping
	@HystrixCommand(fallbackMethod = "getFallBackForAddPizza", commandKey = "msdKeyCompute")
	public ResponseEntity<String> addPizza(@Valid @RequestBody PizzaOrderDto pizzaOrderDTO) {
		return feignClient.addPizza(pizzaOrderDTO);
	}

	/**
	 * Fallback method for addPizza
	 * 
	 * @return
	 */
	public ResponseEntity<String> getFallBackForAddPizza(@Valid @RequestBody PizzaOrderDto pizzaOrderDTO) {
		logger.warn(fallbackMsg);
		return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
	}

	/**
	 * Find list of PizzaOrder details by PizzaName
	 * 
	 * @param pizzaNameDto
	 * @return
	 */
	@PostMapping(value = "/pizzaName")
	@HystrixCommand(fallbackMethod = "getFallBackForGetAllDetailsByPizzaName", commandKey = "msdKeyCompute")
	public ResponseEntity<List<PizzaOrderDto>> getAllDetailsByPizzaName(@Valid @RequestBody PizzaNameDto pizzaNameDto) {
		return feignClient.getAllDetailsByPizzaName(pizzaNameDto);
	}

	/**
	 * Fallback method for getAllDetailsByPizaaName
	 * 
	 * @param pizzaOrderDto
	 * @return
	 */
	public ResponseEntity<List<PizzaOrderDto>> getFallBackForGetAllDetailsByPizzaName(
			@Valid @RequestBody PizzaNameDto pizzaNameDto) {
		logger.warn(fallbackMsg);
		return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.EXPECTATION_FAILED);
	}

	/**
	 * Find PizzaOrder list by CustomerContactNumber
	 * 
	 * @param pizzaCustContactNumberDto
	 * @return
	 */
	@PostMapping(value = "customerContactNumber")
	@HystrixCommand(fallbackMethod = "getFallBackForGetOrderDetailsByContactNumber", commandKey = "msdKeyCompute")
	public ResponseEntity<List<PizzaOrderDto>> getOrderDetailsByContactNumber(
			 @Valid @RequestBody PizzaCustomerContactNumberDto pizzaCustContactNumberDto) {
		return feignClient.getOrderDetailsByContactNumber(pizzaCustContactNumberDto);
	}

	/**
	 * Fallback method for getOrderDetailsByContactNumber
	 * 
	 * @param pizzaCustContactNumberDto
	 * @return
	 */
	public ResponseEntity<List<PizzaOrderDto>> getFallBackForGetOrderDetailsByContactNumber(
			PizzaCustomerContactNumberDto pizzaCustContactNumberDto) {
		logger.warn(fallbackMsg);
		return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.EXPECTATION_FAILED);
	}
}
