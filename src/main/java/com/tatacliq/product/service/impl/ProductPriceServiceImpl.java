package com.tatacliq.product.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatacliq.product.dao.ProductRepository;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;
import com.tatacliq.product.error.ProductException;
import com.tatacliq.product.error.ProductNotFoundException;
import com.tatacliq.product.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public Product saveProductPrice(Long productId, ProductPrice productPrice)
			throws ProductException, ProductNotFoundException {
		try {
			Optional<Product> product = productRepository.findById(productId);
			if (product.isPresent()) {
				Product productDetails = product.get();
				productDetails.setProductPrice(productPrice);
				return productRepository.save(productDetails);
			} else {
				throw new ProductNotFoundException("Product not found with product id : " + productId);
			}
		} catch (Exception e) {
			throw new ProductException("Error when saving the product price for product id " + productId);
		}

	}

	@Override
	public Product getProductPrice(Long productId) throws ProductException,ProductNotFoundException {
		try {
			Optional<Product> product = productRepository.findById(productId);
			if (product.isPresent()) {
				return product.get();
			} else {
				throw new ProductNotFoundException("Product price not found for product id " + productId);
			}
		} catch (Exception e) {
			throw new ProductException("Error when getting the product price for product id " + productId);
		}
	}

}
