package com.luv2code.ecommerce.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")

//commented, manually add getter setter constrtr to get proper json data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "product_id")
	private long productId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
//like see class name
//MANY orderitem[.java] will have one ORDER[.java]
//hence @ManyToOne
	
	
	
	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getImageUrl() {
		return imageUrl;
	}




	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}




	public BigDecimal getUnitPrice() {
		return unitPrice;
	}




	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}




	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public long getProductId() {
		return productId;
	}




	public void setProductId(long productId) {
		this.productId = productId;
	}




	public OrderItem(long id, String imageUrl, BigDecimal unitPrice, int quantity, long productId) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.productId = productId;
	}

	public OrderItem() {}



	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", imageUrl=" + imageUrl + ", unitPrice=" + unitPrice + ", quantity=" + quantity
				+ ", productId=" + productId + "]";
	}
	

	
	
}
