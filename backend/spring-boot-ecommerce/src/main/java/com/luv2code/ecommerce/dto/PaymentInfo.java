package com.luv2code.ecommerce.dto;

import lombok.Data;


public class PaymentInfo {

	  private int amount;
	  private String currency;
	  private String receiptEmail;
	
	  public String getReceiptEmail() {
		return receiptEmail;
	}

	public void setReceiptEmail(String receiptEmail) {
		this.receiptEmail = receiptEmail;
	}

	public int getAmount() {
		return amount;
	}
	
	  public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	


	
	public PaymentInfo(int amount, String currency, String receiptEmail) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.receiptEmail = receiptEmail;
	}

	public PaymentInfo() {}

	@Override
	public String toString() {
		return "PaymentInfo [amount=" + amount + ", currency=" + currency + "]";
	}
	  
}
