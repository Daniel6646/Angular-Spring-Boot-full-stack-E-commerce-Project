package com.luv2code.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
//@Data commented, manually add getter setter constrtr to get proper json data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "order_tracking_number")
	private String orderTrackingNumber;

	@Column(name = "total_quantity")
	private int totalQuantity;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "status")
	private String status;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	// one order will have many order items hence order onetomany orderItems
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order") // order will be a field to be added in orderitems entity
																// class
	private Set<OrderItem> orderItem = new HashSet<>();
	//like refer class name
	//one ORDER will have MANY orderitems hence @OneToMany

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
	private Address shippingAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", referencedColumnName = "id")
	private Address billingAddress;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

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

	
	
	
	public Order() {}
	
	public Order(long id, String orderTrackingNumber, int totalQuantity, BigDecimal totalPrice, String status,
			Date dateCreated, Date lastUpdated, Set<OrderItem> orderItem, Customer customer, Address shippingAddress,
			Address billingAddress) {
		super();
		this.id = id;
		this.orderTrackingNumber = orderTrackingNumber;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.orderItem = orderItem;
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTrackingNumber=" + orderTrackingNumber + ", totalQuantity=" + totalQuantity
				+ ", totalPrice=" + totalPrice + ", status=" + status + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + ", orderItem=" + orderItem + ", customer=" + customer
				+ ", shippingAddress=" + shippingAddress + ", billingAddress=" + billingAddress + "]";
	}

	public void addOrder(OrderItem item) {

		if (item != null) {

			if (orderItem == null) {

				orderItem = new HashSet<>();
			}

			orderItem.add(item);
			item.setOrder(this);
		}

	}

	
	
}
