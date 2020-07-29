package com.tatacliq.product.service;

import javax.validation.Valid;

import com.tatacliq.product.entities.Product;
import com.tatacliq.product.error.ProductException;
import com.tatacliq.product.error.ProductNotFoundException;


public interface ProductService {
	
	public Product saveProduct(Product product) throws ProductException;
	
	public Product getProductDetails(Long productId) throws RuntimeException, Exception;

	public Product updateProduct(Long productId,  @Valid Product product) throws ProductNotFoundException, ProductException;

}
