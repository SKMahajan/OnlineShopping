package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Order_Line_Item;

@Service
public interface OrderLineItemService {
	public List<Order_Line_Item> getOrderLineItemList();
}
