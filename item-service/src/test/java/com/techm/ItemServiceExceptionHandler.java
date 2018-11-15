package com.techm;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.techm.entity.Item;
import com.techm.exception.ItemNotFoundException;

@ControllerAdvice
public class ItemServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ItemNotFoundException.class)
	protected ResponseEntity<Item> handleEntityNotFound(ItemNotFoundException e){
		//Apierror name = new Apierror();
		return null;
	}
}
