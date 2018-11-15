package com.techm.controller;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.techm.entity.Customer_SOS;
import com.techm.entity.Order;
import com.techm.entity.Order_Line_Item;
import com.techm.service.OrderLineItemService;
import com.techm.service.SalesOrderService;

@RestController
public class SalesOrderController {
	
	@Autowired
	RestTemplate rest;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	OrderLineItemService orderLineItemService;
	
	@Autowired
	SalesOrderService salesOrderService;
	
	@PostMapping("/orders")	
	public String createOrder(@RequestBody Order order) {
			String s=salesOrderService.add(order);
			return s;
	}
	
	@GetMapping("/orders")
	public List<Order_Line_Item> getOrder() {
		return orderLineItemService.getOrderLineItemList();
		//return (List<Order_Line_Item>)orderLineItemRepository.findAll();
		
	}
	
	@RabbitListener(queues = "customer-created")
	public void process(Customer_SOS customer) {
		System.out.println(">>>>>>>>> " + customer);
		salesOrderService.addCustomer(customer);
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello!!";
	}
}
