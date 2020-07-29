package com.tatacliq.product.service;

import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;
import com.tatacliq.product.error.ProductException;
import com.tatacliq.product.error.ProductNotFoundException;

public interface ProductPriceService {

	public Product saveProductPrice(Long productId, ProductPrice productPrice) throws ProductException, ProductNotFoundException;

	public Product getProductPrice(Long productPriceId) throws ProductException, ProductNotFoundException;

}
