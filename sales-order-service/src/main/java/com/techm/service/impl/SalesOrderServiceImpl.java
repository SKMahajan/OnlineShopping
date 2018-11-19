package com.techm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.techm.entity.Customer_SOS;
import com.techm.entity.Item;
import com.techm.entity.Order;
import com.techm.entity.Order_Line_Item;
import com.techm.entity.Sales_Order;
import com.techm.exception.CustomerNotFoundException;
import com.techm.exception.ItemNotFoundException;
import com.techm.repository.CustomerRepository;
import com.techm.repository.OrderLineItemRepository;
import com.techm.service.SalesOrderService;

@Service
@EnableCircuitBreaker
public class SalesOrderServiceImpl implements SalesOrderService {

	@Autowired
	OrderLineItemRepository orderLineItemRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
//	@Value("${url}")
	String url;
	
	@Autowired
	RestTemplate restTemplate;	
	
	public Customer_SOS addCustomer(Customer_SOS customer) {
		return customerRepository.save(customer);
	}

	public String  add(Order order) throws CustomerNotFoundException{

		Map<String,Integer> itemMap=order.getItem();		
		if(!validateCustomer(order.getCustomerId())) {
			throw new CustomerNotFoundException("Customer not found with id = "+order.getCustomerId());
		}
		
		Set<String> itemNameSet=itemMap.keySet();
		Set<String> newItemNameSet= new HashSet<String>();
		
		int price=0;
		
		for(String name:itemNameSet) {
			url = getServiceUrl("ITEM-SERVICE");
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+url);
			Item item= restTemplate.getForObject(url+"/items/{name}", Item.class,name);
			if(item!=null) {
				price=price+(item.getPrice()*itemMap.get(name));
				newItemNameSet.add(name);
			}else {
				throw new ItemNotFoundException("The item is not present with name : "+name);
			}
		}
		Date date=new Date(order.getOrderDate().getTime());
		Sales_Order s= new Sales_Order(date,order.getCustomerId(),order.getOrderDescription(),price);
	
		for(String name:newItemNameSet) {
		Order_Line_Item o= new Order_Line_Item( name, itemMap.get(name), s);
		orderLineItemRepository.save(o);
		}
		return "Order received successfuly";
	}
	
	public boolean validateCustomer(Long id) {		
		Optional<Customer_SOS> cust=customerRepository.findById(id);
		if(cust.isPresent()) {
			return true;
		}
		return false;
	}
	
	public String getServiceUrl(String serviceId) {
		ServiceInstance instance = loadBalancerClient.choose(serviceId); 
		String url = instance.getUri().toString();
		return url;
	}

	@HystrixCommand(fallbackMethod="getDefaultItemList")
//			,
			//onFallbackError="failoverMethod",
//			commandProperties={
//			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
//			@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
//		})
	public List<Item> getItemList() {
		String url = getServiceUrl("ITEM-SERVICE");
		List<Item> itemList = restTemplate.getForObject(url+"/items", List.class);
		return itemList;
	}
	
	public List<Item> getDefaultItemList() {
		List<Item> defaultItemList = new ArrayList<>();
		defaultItemList.add(new Item("AC", "AC", 100));
		defaultItemList.add(new Item("Laptop", "Laptop", 500));
		defaultItemList.add(new Item("HD", "HD", 120));

		return defaultItemList;
	}
	
}
