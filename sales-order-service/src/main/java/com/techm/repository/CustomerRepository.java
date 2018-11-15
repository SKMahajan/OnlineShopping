package com.techm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.techm.entity.Customer_SOS;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer_SOS, Long> {

}
