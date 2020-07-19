package com.tatacliq.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bazaarvoice.jolt.JsonUtils;
import com.tatacliq.product.dao.ProductRepository;
import com.tatacliq.product.datamapper.DataMapper;
import com.tatacliq.product.entities.Product;
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
	public Product saveProduct(Product product) {
		product.setCreatedAt(new Date());
		product.setUpdatedAt(new Date());
		return productRepository.save(product);
	}

	@Override
	public Product getProductDetails(Long productId) throws RuntimeException, Exception {
		Optional<Product> findById = productRepository.findById(productId);
		Product product = findById.isPresent() ? findById.get() : null;
//		List specFile = (List) JsonUtils.classpathToList(mappingFileName);
//		String productJson = mapper.mapData(JsonUtils.toJsonString(product), specFile);

		return product;
	}

	@Override
	@Transactional
	public Product updateProduct(Long productId, @Valid Product product) {
		return productRepository.save(product);
	}

}
