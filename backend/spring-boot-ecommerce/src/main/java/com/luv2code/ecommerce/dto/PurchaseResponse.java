package com.luv2code.ecommerce.dto;

import lombok.Data;

public class PurchaseResponse {

// PURPOSE : Use this class to send back a java object as json	

	private String orderTrackingNumber;

	
	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public PurchaseResponse(String orderTrackingNumber) {
		super();
		this.orderTrackingNumber = orderTrackingNumber;
	}

	@Override
	public String toString() {
		return "PurchaseResponse [orderTrackingNumber=" + orderTrackingNumber + "]";
	}

	
	
}
