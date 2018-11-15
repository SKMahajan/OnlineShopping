package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Customer;

@Service
public interface CustomerService {

	List<Customer> getCustomerList();

	Customer getCustomerById(Long id);

	Customer createCustomer(Customer cust);

	Customer updateCustomer(Customer cust);

	void deleteCustomer(Long id);

}