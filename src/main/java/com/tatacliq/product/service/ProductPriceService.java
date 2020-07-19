package com.tatacliq.product.service;

import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;

public interface ProductPriceService {

	public Product saveProductPrice(Long productId, ProductPrice productPrice);

	public Product getProductPrice(Long productPriceId);

}
