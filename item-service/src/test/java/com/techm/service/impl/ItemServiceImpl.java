package com.techm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.entity.Item;
import com.techm.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<Item> getItemList(){
		Iterable<Item> it = itemRepository.findAll();
		List<Item> itemList = new ArrayList<>();
		for(Item i : it) {
			itemList.add(i);
		}
		return itemList;
	}
	@Override
	public Item getItemByName(String itemName) {
		return itemRepository.findByName(itemName);
	}
	@Override
	public Item getItemById(Long id) {
		
		return itemRepository.findById(id).get();
	}
	@Override
	public Item createItem(Item item) {
		Item createdItem = itemRepository.save(item);
		return createdItem;
	}
	@Override
	public Item updateItem(Item item) {
		
		return null;
	}
	@Override
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}
}
