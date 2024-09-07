package com.api.grocery.controller.exception;

public class ProductException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductException(String message) {
		super(message);
	}

}
