package com.techm.service.impl;

import java.util.List;

import com.techm.entity.Item;

public interface ItemService {

	List<Item> getItemList();

	Item getItemByName(String itemName);

	Item createItem(Item item);

	Item updateItem(Item item);

	void deleteItem(Long id);

	Item getItemById(Long id);

}