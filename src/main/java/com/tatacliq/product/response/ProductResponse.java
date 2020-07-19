package com.tatacliq.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tatacliq.product.entities.Product;

public class ProductResponse {

	private String status;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Product product;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ErrorMessage errorMessage;

	public ProductResponse() {
	}
	
	public ProductResponse(Product product, ErrorMessage errorMessage) {
		this.product = product;
		this.errorMessage = errorMessage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
