package com.techm.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public String handleCustomerNotFound(CustomerNotFoundException e) {
		String msg = e.getMessage();
		return msg;
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public String handleItemNotFound(ItemNotFoundException e) {
		return e.getMessage();
		
	}

}