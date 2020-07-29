package com.tatacliq.product.controller;

import java.io.File;
import java.net.URI;
import java.util.Locale;

import javax.validation.Valid;

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

	/**
	 * This rest API is to get the list of all the products
	 * 
	 * @param productId
	 * @return Response with Product details
	 */
	@GetMapping("/api/v1/products/{productId}")
	public ResponseEntity<ProductResponse> getProductDetails(@PathVariable(value = "productId") Long productId) {
		ProductResponse productResponse = new ProductResponse();
		try {

			Product productDetails = productService.getProductDetails(productId);

			if (productDetails == null) {
				productResponse.setStatus(ResponseStatus.FAILED)
						.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(), messageSource
								.getMessage(Constants.PRODUCT_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
			} else {
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(productDetails);
				return ResponseEntity.status(HttpStatus.OK).body(productResponse);
			}

		} catch (ProductNotFoundException e) {
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(),
					messageSource.getMessage(Constants.PRODUCT_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
		} catch (Exception e) {
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
		ProductResponse productResponse = new ProductResponse();
		Product savedProduct = null;
		try {
			savedProduct = productService.saveProduct(product);

			productResponse.setStatus(ResponseStatus.SUCCESS).setItem(savedProduct);
			ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
			builder.path(File.separator + savedProduct.getProductId().toString());
			URI location = builder.build().toUri();
			return ResponseEntity.created(location).body(productResponse);
		} catch (ProductException e) {
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
		ProductResponse productResponse = new ProductResponse();
		Product savedProduct = null;
		try {
			savedProduct = productService.updateProduct(productId, product);
			if (savedProduct != null) {
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(savedProduct);
				return ResponseEntity.status(HttpStatus.OK).body(productResponse);
			} else {
				productResponse.addErrorMessage(
						new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource.getMessage(
								Constants.PRODUCT_NOT_UPDATED, new Object[] { productId }, Locale.ENGLISH)));

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);

			}

		} catch (ProductNotFoundException e) {
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(),
					messageSource.getMessage(Constants.PRODUCT_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
		} catch (Exception e) {
			productResponse.addErrorMessage(new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(),
					messageSource.getMessage(Constants.PRODUCT_ERROR, new Object[] { productId }, Locale.ENGLISH)));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}