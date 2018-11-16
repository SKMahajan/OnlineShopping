package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Customer_SOS;
import com.techm.entity.Item;
import com.techm.entity.Order;

@Service
public interface SalesOrderService {
	public String add(Order order);
	public Customer_SOS addCustomer(Customer_SOS customer);
	public List<Item> getItemList();
}
