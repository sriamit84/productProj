package com.tatacliq.product.response;

import com.tatacliq.product.entities.Data;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ResponseStatus;

public class ProductResponse {

	private String status;

	public String getStatus() {
		return status;
	}

	public Data<Product> getData() {
		return data;
	}

	private Data<Product> data;

	public ProductResponse() {
		data = new Data<Product>();
	}

	public ProductResponse setStatus(ResponseStatus status) {
		this.status = status.getCode();
		return this;
	}

	public ProductResponse setItem(Product product) {
		this.data.setItem(product);
		return this;
	}

	public ProductResponse addErrorMessage(ErrorMessage errorMessage) {
		this.data.addErrorMessage(errorMessage);
		return this;
	}

}
