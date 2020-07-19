package com.tatacliq.product;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

		String json = mapToJson(product);
		ProductResponse productResponse = new ProductResponse(product, null);
		when(productController.saveProduct(product)).thenReturn(productResponse);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isOk());
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
		ProductResponse productResponse = new ProductResponse(product, null);
		when(productController.getProductDetails(1L)).thenReturn(productResponse);
		mockMvc.perform(MockMvcRequestBuilders.get("/products/1")).andExpect(status().isOk()).andExpect(status().isOk())
				.andExpect(jsonPath("$.product.sellerId").value(1));
		;
		;
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
		ProductResponse productResponse = new ProductResponse(product, null);
		when(productController.getProductDetails(1L)).thenReturn(productResponse);

		String json = mapToJson(product);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/products/1").contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
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
		ProductResponse productResponse = new ProductResponse(product, null);
		when(productPriceController.saveProductPrice(1L, productPrice)).thenReturn(productResponse);
		mockMvc.perform(MockMvcRequestBuilders.post("/products/1/price").contentType(MediaType.APPLICATION_JSON_VALUE)
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

		ProductResponse productResponse = new ProductResponse(product, null);

		when(productPriceController.getProductPrice(1L)).thenReturn(productResponse);
		mockMvc.perform(MockMvcRequestBuilders.get("/products/1/price")).andExpect(status().isOk())
				.andExpect(jsonPath("$.product.productPrice.min").value(10.90));
		;
	}

}
