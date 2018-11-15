package com.techm.exception;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6255500566710488160L;
	
	ItemNotFoundException(String message){
		super(message);
	}

}
