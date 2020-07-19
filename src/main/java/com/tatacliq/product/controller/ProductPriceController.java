package com.tatacliq.product.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;
import com.tatacliq.product.response.ErrorMessage;
import com.tatacliq.product.response.ProductResponse;
import com.tatacliq.product.service.ProductPriceService;

/**
 * @author Amit Srivastava
 *
 */
@RestController
public class ProductPriceController {

	@Autowired
	private ProductPriceService productPriceService;

	/**
	 * This rest API is to get the list of all the products
	 * 
	 * @param productId
	 * @param pageable
	 * @return Page Response with Product details
	 */

	@GetMapping("/products/{productId}/price")
	public ProductResponse getProductPrice(@PathVariable(value = "productId") Long productId) {

		ErrorMessage errorMessage = null;
		ProductResponse productResponse = new ProductResponse();
		try {
			Product product = productPriceService.getProductPrice(productId);
			productResponse = new ProductResponse(product, errorMessage);
			productResponse.setStatus("200");
			if (product == null) {
				errorMessage = new ErrorMessage("400", "Product is not found");
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
	@PostMapping("/products/{productId}/price")
	public ProductResponse saveProductPrice(@PathVariable(value = "productId") Long productId,
			@Valid @RequestBody ProductPrice productPrice) {
		ErrorMessage errorMessage = null;
		ProductResponse productResponse = new ProductResponse();
		try {
			Product product = productPriceService.saveProductPrice(productId, productPrice);
			if (product == null) {
				errorMessage = new ErrorMessage("400", "Product is not found with id " + productId);
				productResponse.setErrorMessage(errorMessage);
			} else {
				productResponse.setMessage("Product Price Created Successfully");
				productResponse.setStatus("202");
			}
		} catch (Exception e) {
			errorMessage = new ErrorMessage("400", "There was an issue when fetching the product");
		}
		return productResponse;
	}

}