package com.sprintstrickers.mycredit.exception;

public class AgeException extends Exception {

	/**
	 * @author Sri Keerthna
	 * @since 2019-12-23
	 * If the age is less than 21 it will throw an error and it is handled in this method
	 */
	private static final long serialVersionUID = 1L;

	public AgeException(String message) {
		super(message);
	}
}
