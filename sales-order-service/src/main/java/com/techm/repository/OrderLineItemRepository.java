package com.techm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.techm.entity.Order_Line_Item;

public interface OrderLineItemRepository extends PagingAndSortingRepository<Order_Line_Item, Long> {

}
