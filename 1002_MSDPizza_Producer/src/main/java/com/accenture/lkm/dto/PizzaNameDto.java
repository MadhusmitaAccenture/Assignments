package com.accenture.lkm.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class PizzaNameDto {
	
	@NotEmpty
	private String pizzaName;

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

}
