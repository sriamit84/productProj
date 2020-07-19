package com.tatacliq.product.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatacliq.product.dao.ProductRepository;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;
import com.tatacliq.product.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {


	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public Product saveProductPrice(Long productId, ProductPrice productPrice) {
		if (productRepository.findById(productId).isPresent()) {
			Product product = productRepository.findById(productId).get();
			product.setProductPrice(productPrice);
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public Product getProductPrice(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		return product != null ? product.get() : null;
	}

}
