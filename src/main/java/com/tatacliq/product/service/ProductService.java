package com.tatacliq.product.service;

import javax.validation.Valid;

import com.tatacliq.product.entities.Product;


public interface ProductService {
	
	public Product saveProduct(Product product);
	
	public Product getProductDetails(Long productId) throws RuntimeException, Exception;

	public Product updateProduct(Long productId,  @Valid Product product);

}
