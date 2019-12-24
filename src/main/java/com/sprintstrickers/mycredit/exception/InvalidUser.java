package com.sprintstrickers.mycredit.exception;

public class InvalidUser extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InvalidUser(){
		
	}
	public InvalidUser(String message) {
		super(message);
	}
}
