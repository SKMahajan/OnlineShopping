package com.techm.entity;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {
	
	private String orderDescription;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date orderDate;
	private Long customerId;
	private Map<String,Integer> item;
	public Order() {
	}
	public Order(String orderDescription, Date orderDate, Long customerId, Map<String,Integer> item) {
		this.orderDescription = orderDescription;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.item = item;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Map<String, Integer> getItem() {
		return item;
	}
	public void setItem(Map<String, Integer> item) {
		this.item = item;
	}
}