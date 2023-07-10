package com.accenture.lkm.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.accenture.lkm.model.PizzaOrderDto;

@FeignClient(name = "cst-msdpizza-producer")
public interface MyFeignClient {

	@GetMapping(value = "pizzaorder")
	ResponseEntity<List<PizzaOrderDto>> getPizzaDetails();
	
	
	
	/*
	 * @RequestMapping(value = "/emp/controller/getDetailsById/{id}", method =
	 * RequestMethod.GET) ResponseEntity<Employee>
	 * findByEmployeeId(@PathVariable("id") Integer employeeId);
	 * 
	 * @RequestMapping(value = "/emp/controller/addEmp", method =
	 * RequestMethod.POST) ResponseEntity<String> addEmployee(@RequestBody Employee
	 * employee);
	 * 
	 * @RequestMapping(value = "/emp/controller/updateEmp", method =
	 * RequestMethod.PUT) ResponseEntity<Employee> updateEmployee(@RequestBody
	 * Employee employee);
	 * 
	 * @RequestMapping(value = "/emp/controller/deleteEmp/{id}", method =
	 * RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	 * ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int myId);
	 */
}
