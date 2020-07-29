package com.tatacliq.product.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductException() {
		super();
	}

	public ProductException(String message) {
		super(message);
	}

	public ProductException(String message, Throwable cause) {
		super(message, cause);
	}

}
