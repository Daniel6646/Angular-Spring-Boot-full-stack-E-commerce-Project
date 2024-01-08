package com.luv2code.ecommerce.dto;

import java.util.Set;

import com.luv2code.ecommerce.entity.Address;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;

public class Purchase {

//PURPOSE : pruchase has rltnshp to customer shipping adress, billing address, order and collection of orderitems	
	
	
	private Customer customer;
	
	private Address shippingAddress;
	
	private Address billingAddress;
	
	private Order order;
	
	private Set<OrderItem> orderItems;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOderItems(Set<OrderItem> oderItems) {
		this.orderItems = oderItems;
	}

	public Purchase(Customer customer, Address shippingAddress, Address billingAddress, Order order,
			Set<OrderItem> oderItems) {
		super();
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.order = order;
		this.orderItems = oderItems;
	}

	@Override
	public String toString() {
		return "Purchase [customer=" + customer + ", shippingAddress=" + shippingAddress + ", billingAddress="
				+ billingAddress + ", order=" + order + ", orderItems=" + orderItems + "]";
	}
	
	
}


