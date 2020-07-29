package com.tatacliq.product.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tatacliq.product.entities.HTTPStatus;
import com.tatacliq.product.entities.ResponseStatus;
import com.tatacliq.product.response.ErrorMessage;
import com.tatacliq.product.response.ProductResponse;

@ControllerAdvice
@RestController
public class ProductResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProductResponse productResponse = new ProductResponse();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			ErrorMessage errorDetails = new ErrorMessage(HTTPStatus.BAD_REQUEST.getCode(), error.getDefaultMessage());
			productResponse.addErrorMessage(errorDetails);
		});

		if (ex.getBindingResult().hasErrors()) {
			productResponse.setStatus(ResponseStatus.FAILED);
		}

		return new ResponseEntity<Object>(productResponse, HttpStatus.BAD_REQUEST);
	}

}