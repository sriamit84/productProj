package com.tatacliq.product.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tatacliq.product.dao.ProductRepository;
import com.tatacliq.product.datamapper.DataMapper;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.error.ProductException;
import com.tatacliq.product.error.ProductNotFoundException;
import com.tatacliq.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${mappingFileName}")
	String mappingFileName;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	DataMapper mapper;

	@Override
	@Transactional
	public Product saveProduct(Product product) throws ProductException {
		try {
			return productRepository.save(product);
		} catch (Exception e) {
			throw new ProductException("Error when saving the product with details :" + product.toString());
		}

	}

	@Override
	public Product getProductDetails(Long productId) throws ProductException {
		try {
			Optional<Product> productDetails = productRepository.findById(productId);
			if (productDetails.isPresent())
				return productDetails.get();
			else {
				throw new ProductNotFoundException("Product with id " + productId + " not found");
			}
		} catch (Exception e) {
			throw new ProductException("Error when fetching the product with id :" + productId);
		}
	}

	@Override
	@Transactional
	public Product updateProduct(Long productId, @Valid Product product)
			throws ProductNotFoundException, ProductException {
		try {
			Optional<Product> productDetails = productRepository.findById(productId);
			if (productDetails.isPresent()) {
				return productRepository.save(product);
			} else {
				throw new ProductNotFoundException("Product with id " + productId + " not found");
			}
		} catch (Exception e) {
			throw new ProductException("Error when updating the product with id :" + productId);
		}
	}

}
