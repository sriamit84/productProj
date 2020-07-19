package com.tatacliq.product.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tatacliq.product.entities.Product;
import com.tatacliq.product.response.ErrorMessage;
import com.tatacliq.product.response.ProductResponse;
import com.tatacliq.product.service.ProductService;

/**
 * @author Amit Srivastava
 *
 */
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * This rest API is to get the list of all the products
	 * 
	 * @param productId
	 * @return Response with Product details
	 */
	@GetMapping("/products/{productId}")
	public ProductResponse getProductDetails(@PathVariable(value = "productId") Long productId) {
		ErrorMessage errorMessage = null;
		ProductResponse productResponse = new ProductResponse();
		try {

			Product productDetails = productService.getProductDetails(productId);

			if (productDetails == null) {
				errorMessage = new ErrorMessage("400", "Product is null");
			} else {
				productResponse = new ProductResponse(productDetails, errorMessage);
				productResponse.setStatus("200");
			}
		} catch (Exception e) {
			errorMessage = new ErrorMessage("400", "There was an issue when fetching the product");
		}
		return productResponse;
	}

	/**
	 * This rest API is to add a product
	 * 
	 * @param product
	 * @return {@link Product}
	 */
	@PostMapping("/products")
	public ProductResponse saveProduct(@Valid @RequestBody Product product) {
		ErrorMessage errorMessage = null;
		ProductResponse productResponse = new ProductResponse();
		try {
			Product savedProduct = productService.saveProduct(product);
			if (savedProduct == null) {
				errorMessage = new ErrorMessage("400", "Product is not found");
				productResponse.setErrorMessage(errorMessage);
			} else {
				productResponse.setMessage("Product Created Successfully");
				productResponse.setStatus("202");
			}
		} catch (Exception e) {
			errorMessage = new ErrorMessage("400", "There was an issue when fetching the product");
		}
		return productResponse;

	}

	/**
	 * This rest is to update the product details
	 * 
	 * @param productId
	 * @param product
	 * @return {@link Product}
	 */
	@PutMapping("/products/{productId}")
	public ProductResponse updateProductDetails(@PathVariable(value = "productId") Long productId,
			@Valid @RequestBody Product product) {
		ErrorMessage errorMessage = null;
		ProductResponse productResponse = new ProductResponse();
		try {

			Product savedProduct = productService.updateProduct(productId, product);
			if (savedProduct == null) {
				errorMessage = new ErrorMessage("400", "Product is not found");
				productResponse.setErrorMessage(errorMessage);
			} else {
				productResponse.setMessage("Product Updated Successfully");
				productResponse.setStatus("200");
			}
		} catch (Exception e) {
			errorMessage = new ErrorMessage("400", "There was an issue when updating the product");
		}
		return productResponse;
	}

}