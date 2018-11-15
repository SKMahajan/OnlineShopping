package com.techm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.entity.Customer;
import com.techm.repository.CustomerRepository;
import com.techm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> getCustomerList() {
		List<Customer> custList = null;
		custList = (List<Customer>) customerRepository.findAll();
		return custList;
	}

	@Override
	public Customer getCustomerById(Long id) {
		Customer customer = null;
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			// throw new Exception("Customer not found!!");
		}
		return customer;
	}

	@Override
	public Customer createCustomer(Customer cust) {
		Customer customer = customerRepository.save(cust);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer cust) {
		Customer customer = null;
		if (cust.getId() != null) {

			Optional<Customer> optional = customerRepository.findById(cust.getId());
			if (optional.isPresent()) {
				customer = new Customer(optional.get());
				customerRepository.save(customer);
			}
		}
		return customer;
	}

	@Override
	public void deleteCustomer(Long id) {
		// Customer customer = null;
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			customerRepository.deleteById(id);
		}
	}
}
