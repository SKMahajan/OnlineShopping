package com.techm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.techm.entity.Sales_Order;

public interface SalesOrderRepository extends PagingAndSortingRepository<Sales_Order, Long>{

}
