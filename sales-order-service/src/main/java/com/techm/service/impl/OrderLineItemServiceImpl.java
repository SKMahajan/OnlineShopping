package com.techm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.entity.Order_Line_Item;
import com.techm.repository.OrderLineItemRepository;
import com.techm.service.OrderLineItemService;

@Service
public class OrderLineItemServiceImpl implements OrderLineItemService{

	@Autowired
	OrderLineItemRepository orderLineItemRepository;
	
	public List<Order_Line_Item> getOrderLineItemList(){
		
		Iterable<Order_Line_Item> it = orderLineItemRepository.findAll();
		List<Order_Line_Item> orderLineItemList = new ArrayList<>();
		for(Order_Line_Item i : it) {
			orderLineItemList.add(i);
		}
		return orderLineItemList;
		
	}

}
