package com.accenture.lkm.model;

public class PizzaOrderDto {
	private Integer orderId;
	private String pizzaName;
	private double bill;
	private Integer quantity;
	private String customerContactNumber;

	public PizzaOrderDto() {
		super();
	}

	public PizzaOrderDto(Integer orderId, String pizzaName, double bill, Integer quantity,
			String customerContactNumber) {
		super();
		this.orderId = orderId;
		this.pizzaName = pizzaName;
		this.bill = bill;
		this.quantity = quantity;
		this.customerContactNumber = customerContactNumber;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCustomerContactNumber() {
		return customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	@Override
	public String toString() {
		return "PizzaOrderEntity [" + (orderId != null ? "orderId=" + orderId + ", " : "")
				+ (pizzaName != null ? "pizzaName=" + pizzaName + ", " : "") + "bill=" + bill + ", "
				+ (quantity != null ? "quantity=" + quantity + ", " : "")
				+ (customerContactNumber != null ? "customerContactNumber=" + customerContactNumber : "") + "]";
	}

}
