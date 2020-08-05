package com.tatacliq.product.controller;

import java.io.File;
import java.net.URI;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tatacliq.product.constants.Constants;
import com.tatacliq.product.entities.HTTPStatus;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ResponseStatus;
import com.tatacliq.product.error.ProductException;
import com.tatacliq.product.error.ProductNotFoundException;
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
	private MessageSource messageSource;
	@Autowired
	private ProductService productService;

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * This rest API is to get the list of all the products
	 * 
	 * @param productId
	 * @return Response with Product details
	 */
	@GetMapping("/api/v1/products/{productId}")
	public ResponseEntity<ProductResponse> getProductDetails(@PathVariable(value = "productId") Long productId) {
		long startTime = System.currentTimeMillis();
		logger.info("START: getProductDetails Service is called");
		ProductResponse productResponse = new ProductResponse();
		try {

			Product productDetails = productService.getProductDetails(productId);

			if (productDetails == null) {
				logger.error("product not found for the product id : " + productId);
				productResponse.setStatus(ResponseStatus.FAILED)
						.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(), messageSource
								.getMessage(Constants.PRODUCT_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
			} else {
				logger.debug("got the product details : " + productDetails.toString());
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(productDetails);
				long endTime = System.currentTimeMillis();
				logger.info("END: getProductDetails Service is completed in " + (endTime - startTime) + " ms");
				return ResponseEntity.status(HttpStatus.OK).body(productResponse);

			}

		} catch (ProductNotFoundException e) {
			logger.error(e.getMessage());
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(),
					messageSource.getMessage(Constants.PRODUCT_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
		} catch (Exception e) {
			logger.error(e.getMessage());
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(),
					messageSource.getMessage(Constants.PRODUCT_ERROR, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);
		}
	}

	/**
	 * This rest API is to add a product
	 * 
	 * @param product
	 * @return {@link Product}
	 */
	@PostMapping("/api/v1/products")
	public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody Product product) {
		long startTime = System.currentTimeMillis();
		logger.info("START: saveProduct Service is called");
		ProductResponse productResponse = new ProductResponse();
		Product savedProduct = null;
		try {
			savedProduct = productService.saveProduct(product);

			if (savedProduct != null) {
				ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
				builder.path(File.separator + savedProduct.getProductId().toString());
				URI location = builder.build().toUri();
				long endTime = System.currentTimeMillis();
				logger.info("END: saveProduct Service is completed in " + (endTime - startTime) + " ms");
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(savedProduct);
				return ResponseEntity.created(location).body(productResponse);
			} else {
				productResponse.addErrorMessage(
						new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource.getMessage(
								Constants.PRODUCT_NOT_CREATED, new Object[] { product.getTitle() }, Locale.ENGLISH)));
				logger.error("Error when called saveProduct Service, updated product is null");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);

			}

		} catch (ProductException e) {
			logger.error(e.getMessage());
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource
					.getMessage(Constants.PRODUCT_ERROR, new Object[] { product.getProductId() }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	/**
	 * This rest is to update the product details
	 * 
	 * @param product
	 * @return {@link Product}
	 */
	@PutMapping("/api/v1/products/{productId}")
	public ResponseEntity<ProductResponse> updateProductDetails(@PathVariable(value = "productId") Long productId,
			@Valid @RequestBody Product product) {
		long startTime = System.currentTimeMillis();
		logger.info("START: updateProductDetails Service is called");
		ProductResponse productResponse = new ProductResponse();
		Product savedProduct = null;
		try {
			savedProduct = productService.updateProduct(productId, product);
			if (savedProduct != null) {
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(savedProduct);
				long endTime = System.currentTimeMillis();
				logger.info("END: updateProductDetails Service is completed in " + (endTime - startTime) + " ms");
				return ResponseEntity.status(HttpStatus.OK).body(productResponse);
			} else {
				productResponse.addErrorMessage(
						new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource.getMessage(
								Constants.PRODUCT_NOT_UPDATED, new Object[] { productId }, Locale.ENGLISH)));
				logger.error("Error when called updateProductDetails Service, updated product is null");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);

			}

		} catch (ProductNotFoundException e) {
			logger.error(e.getMessage());
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(),
					messageSource.getMessage(Constants.PRODUCT_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
		} catch (Exception e) {
			logger.error("Error when updating the product details,Error Message :" + e.getMessage());
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(),
					messageSource.getMessage(Constants.PRODUCT_ERROR, new Object[] { productId }, Locale.ENGLISH)));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}