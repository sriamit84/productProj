package com.tatacliq.product.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tatacliq.product.constants.Constants;
import com.tatacliq.product.entities.HTTPStatus;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;
import com.tatacliq.product.entities.ResponseStatus;
import com.tatacliq.product.error.ProductException;
import com.tatacliq.product.error.ProductNotFoundException;
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

	@Autowired
	private MessageSource messageSource;

	/**
	 * This rest API is to get the product price by product Id
	 * 
	 * @param productId
	 * @return Page Response with Product details
	 */

	@GetMapping("/api/v1/products/{productId}/price")
	public ResponseEntity<ProductResponse> getProductPrice(@PathVariable(value = "productId") Long productId) {
		ProductResponse productResponse = new ProductResponse();
		try {
			Product product = productPriceService.getProductPrice(productId);
			if (product == null) {
				productResponse.setStatus(ResponseStatus.FAILED)
						.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(), messageSource.getMessage(
								Constants.PRODUCT_PRICE_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
			} else {
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(product);

			}
			return ResponseEntity.ok().body(productResponse);
		}

		catch (ProductException e) {
			productResponse.setStatus(ResponseStatus.FAILED)
					.addErrorMessage(new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource
							.getMessage(Constants.PRODUCT_PRICE_ERROR, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);
		} catch (ProductNotFoundException e) {
			productResponse.setStatus(ResponseStatus.FAILED)
					.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(), messageSource.getMessage(
							Constants.PRODUCT_PRICE_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
		}
	}

	/**
	 * This rest API is to add a product
	 * 
	 * @param product
	 * @return {@link Product}
	 */
	@PostMapping("/api/v1/products/{productId}/price")
	public ResponseEntity<ProductResponse> saveProductPrice(@PathVariable(value = "productId") Long productId,
			@Valid @RequestBody ProductPrice productPrice) {
		ProductResponse productResponse = new ProductResponse();
		try {
			Product product = productPriceService.saveProductPrice(productId, productPrice);

			if (product != null) {
				productResponse.setStatus(ResponseStatus.SUCCESS).setItem(product);
				ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
				URI location = builder.build().toUri();
				return ResponseEntity.created(location).body(productResponse);
			} else {
				productResponse.setStatus(ResponseStatus.FAILED).setItem(product);
				productResponse.addErrorMessage(
						new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource.getMessage(
								Constants.PRODUCT_PRICE_ERROR, new Object[] { productId }, Locale.ENGLISH)));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);
			}
		} catch (ProductException e) {
			productResponse.setStatus(ResponseStatus.FAILED)
					.addErrorMessage(new ErrorMessage(HTTPStatus.INTERNAL_SERVER_ERROR.getCode(), messageSource
							.getMessage(Constants.PRODUCT_PRICE_ERROR, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(productResponse);
		} catch (ProductNotFoundException e) {
			productResponse.setStatus(ResponseStatus.FAILED)
					.addErrorMessage(new ErrorMessage(HTTPStatus.NOT_FOUND.getCode(), messageSource.getMessage(
							Constants.PRODUCT_PRICE_NOT_FOUND, new Object[] { productId }, Locale.ENGLISH)));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productResponse);
		}

	}

}