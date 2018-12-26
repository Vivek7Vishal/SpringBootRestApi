package com.recrmort.mortgages.exception;

public class BadRequestDataException extends RuntimeException {

	private static final long serialVersionUID = 4305491282371441979L;

	/**
	 * @param message
	 */
	public BadRequestDataException(String message) {
		super(message);
	}

}
