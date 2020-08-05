package com.tatacliq.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tatacliq.product.controller.ProductController;
import com.tatacliq.product.controller.ProductPriceController;
import com.tatacliq.product.entities.MetaField;
import com.tatacliq.product.entities.Product;
import com.tatacliq.product.entities.ProductPrice;
import com.tatacliq.product.response.ProductResponse;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ProductServiceApplicationTests extends AbstractTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductPriceController productPriceController;

	@MockBean
	ProductController productController;

	@Test
	@Order(1)
	public void createProduct() throws Exception {
		Product product = new Product();
		product.setSellerId("1");
		product.setTitle("Test");
		product.setManufacturer("Test");
		product.setIsLowQuantity(false);
		product.setIsBackorder(false);
		product.setIsSoldOut(false);
		MetaField metaField = new MetaField();
		metaField.setKey("1");
		metaField.setValue("Test Value");
		List<MetaField> metaFields = new ArrayList<MetaField>();
		metaFields.add(metaField);
		product.setMetafields(metaFields);

		product.setRequiresShipping(false);
		product.setIsVisible(true);
		product.setPublishedAt(new Date());
		product.setCreatedAt(new Date());
		product.setWorkflowStatus("new");
		ProductResponse productResponse= new ProductResponse().setItem(product);
		
		String json = mapToJson(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.CREATED);
		when(productController.saveProduct(product)).thenReturn(productResponseEntity);
		
	    RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/products")
	            .accept(MediaType.APPLICATION_JSON)
	            .content(json)
	            .contentType(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	@Order(1)
	public void createProductWithValidationError() throws Exception {
		Product product = new Product();
		product.setTitle("Test");
		product.setManufacturer("Test");
		product.setIsLowQuantity(false);
		product.setIsBackorder(false);
		product.setIsSoldOut(false);
		MetaField metaField = new MetaField();
		metaField.setKey("1");
		metaField.setValue("Test Value");
		List<MetaField> metaFields = new ArrayList<MetaField>();
		metaFields.add(metaField);
		product.setMetafields(metaFields);

		product.setRequiresShipping(false);
		product.setIsVisible(true);
		product.setPublishedAt(new Date());
		product.setCreatedAt(new Date());
		product.setWorkflowStatus("new");
		ProductResponse productResponse= new ProductResponse().setItem(product);
		
		String json = mapToJson(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		when(productController.saveProduct(product)).thenReturn(productResponseEntity);
		
	    RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/products")
	            .accept(MediaType.APPLICATION_JSON)
	            .content(json)
	            .contentType(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	@Order(1)
	public void createProductWithValidation() throws Exception {
		Product product = new Product();
		product.setSellerId("1");
		product.setTitle("Test");
		product.setManufacturer("Test");
		product.setIsLowQuantity(false);
		product.setIsBackorder(false);
		product.setIsSoldOut(false);
		MetaField metaField = new MetaField();
		metaField.setKey("1");
		metaField.setValue("Test Value");
		List<MetaField> metaFields = new ArrayList<MetaField>();
		metaFields.add(metaField);
		product.setMetafields(metaFields);

		product.setRequiresShipping(false);
		product.setIsVisible(true);
		product.setPublishedAt(new Date());
		product.setCreatedAt(new Date());
		product.setWorkflowStatus("new");
		ProductResponse productResponse= new ProductResponse().setItem(product);
		
		String json = mapToJson(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.CREATED);
		when(productController.saveProduct(product)).thenReturn(productResponseEntity);
		
	    RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/products")
	            .accept(MediaType.APPLICATION_JSON)
	            .content(json)
	            .contentType(MediaType.APPLICATION_JSON);

	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	@Order(2)
	public void getProductById() throws Exception {

		Product product = new Product();
		product.setSellerId("1");
		product.setTitle("Test");
		product.setManufacturer("Test");
		product.setIsLowQuantity(false);
		product.setIsBackorder(false);
		product.setIsSoldOut(false);
		MetaField metaField = new MetaField();
		metaField.setKey("1");
		metaField.setValue("Test Value");
		List<MetaField> metaFields = new ArrayList<MetaField>();
		metaFields.add(metaField);
		product.setMetafields(metaFields);

		product.setRequiresShipping(false);
		product.setIsVisible(true);
		product.setPublishedAt(new Date());
		product.setCreatedAt(new Date());
		product.setWorkflowStatus("new");
		ProductResponse productResponse= new ProductResponse().setItem(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
		when(productController.getProductDetails(1L)).thenReturn(productResponseEntity);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.item.sellerId").value(1));
	}

	@Test
	@Order(3)
	public void updateProduct() throws Exception {

		Product product = new Product();
		product.setSellerId("1");
		product.setTitle("Test1");
		product.setManufacturer("Test");
		product.setIsLowQuantity(false);
		product.setIsBackorder(false);
		product.setIsSoldOut(false);
		MetaField metaField = new MetaField();
		metaField.setKey("1");
		metaField.setValue("Test Value");
		List<MetaField> metaFields = new ArrayList<MetaField>();
		metaFields.add(metaField);
		product.setMetafields(metaFields);

		product.setRequiresShipping(false);
		product.setIsVisible(true);
		product.setPublishedAt(new Date());
		product.setCreatedAt(new Date());
		product.setWorkflowStatus("new");
		ProductResponse productResponse= new ProductResponse().setItem(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
		when(productController.getProductDetails(1L)).thenReturn(productResponseEntity);

		String json = mapToJson(product);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/v1/products/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isOk());
		;
	}

	@Test
	@Order(4)
	public void saveProductPrice() throws Exception {

		Product product = new Product();
		product.setProductId(1L);
		ProductPrice productPrice = new ProductPrice();
		productPrice.setMax(100.90);
		productPrice.setMin(10.90);
		productPrice.setRange("10-105");
		product.setProductPrice(productPrice);
		String json = mapToJson(product);
		ProductResponse productResponse= new ProductResponse().setItem(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
		when(productPriceController.saveProductPrice(1L, productPrice))
				.thenReturn(productResponseEntity);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/1/price").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json));
	}

	@Test
	@Order(5)
	public void getProductPrice() throws Exception {
		Product product = new Product();
		product.setSellerId("1");
		product.setTitle("Test1");
		product.setManufacturer("Test");
		product.setIsLowQuantity(false);
		product.setIsBackorder(false);
		product.setIsSoldOut(false);
		MetaField metaField = new MetaField();
		metaField.setKey("1");
		metaField.setValue("Test Value");
		List<MetaField> metaFields = new ArrayList<MetaField>();
		metaFields.add(metaField);
		product.setMetafields(metaFields);

		product.setRequiresShipping(false);
		product.setIsVisible(true);
		product.setPublishedAt(new Date());
		product.setCreatedAt(new Date());
		product.setWorkflowStatus("new");
		ProductPrice productPrice = new ProductPrice();
		productPrice.setMax(100.90);
		productPrice.setMin(10.90);
		productPrice.setRange("10-105");
		product.setProductPrice(productPrice);

		ProductResponse productResponse= new ProductResponse().setItem(product);
		ResponseEntity<ProductResponse> productResponseEntity = new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
		when(productPriceController.getProductPrice(1L)).thenReturn(productResponseEntity);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1/price")).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.item.productPrice.min").value(10.90));
		;
	}

}
