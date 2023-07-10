package com.accenture.lkm.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.model.PizzaOrderDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("pizzaorder")
public class ConsumerControllerClient {

	@Autowired
	private MyFeignClient feignClient;

	public static Logger logger = Logger.getLogger(ConsumerControllerClient.class);

	@GetMapping
	@HystrixCommand(fallbackMethod = "getDataFallBack", commandKey = "msdKeyCompute")
	public ResponseEntity<List<PizzaOrderDto>> getPizzaDetails() {
		return feignClient.getPizzaDetails();
	}

	public ResponseEntity<List<PizzaOrderDto>> getDataFallBack() {
		logger.warn("There is some error. Try after sometime! ");
		return new ResponseEntity<List<PizzaOrderDto>>(HttpStatus.EXPECTATION_FAILED);
	}
}
