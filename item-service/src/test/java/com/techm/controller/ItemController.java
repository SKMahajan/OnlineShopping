package com.techm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techm.entity.Item;
import com.techm.service.impl.ItemService;

@RestController
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@DeleteMapping("/items/{id}")
	public void deleteItem(@PathVariable Long id) {
		itemService.deleteItem(id);
	}
	
	@GetMapping("/items")
	public List<Item> getItemList(){
		List<Item> itemList = itemService.getItemList();
		return itemList;
	}
	
	@PostMapping("/items")
	public Item createItem(@RequestBody Item item) {
		return itemService.createItem(item);
	}

	@GetMapping("/hello")
	public String hello() {
		Item i = new Item();
		System.out.println(i);
		return "Success!";
	}
	
	@GetMapping("/items/{itemName}")
	public Item getItemById(@PathVariable String itemName){
		Item item = itemService.getItemByName(itemName);
		return item;
	}
	
}
