package com.accenture.lkm.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PizzaCustomerContactNumberDto {
	
	@NotEmpty
	private String customerContactNumber;
	
	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

}
