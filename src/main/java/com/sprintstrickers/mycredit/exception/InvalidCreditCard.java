package com.sprintstrickers.mycredit.exception;

public class InvalidCreditCard extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InvalidCreditCard(){
		
	}
	public InvalidCreditCard(String message) {
		super(message);
	}
}
