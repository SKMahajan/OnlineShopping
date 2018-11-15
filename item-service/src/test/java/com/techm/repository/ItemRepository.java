package com.techm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.techm.entity.Item;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

	public Item findByName(String name);
}
