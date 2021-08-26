package com.dell.webservice.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.dell.webservice.entity.Product;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller Test")
public class ProductControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int randomPort;

	private int productId;

	@Test
	@DisplayName("GET all products test")
	public void testGetAllProducts() {
		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products";
		// call get api
		ResponseEntity<List> response = testRestTemplate.getForEntity(url, List.class);
		// test response
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(false, response.getBody().isEmpty());
	}

	@Test
	@DisplayName("GET one product test")
	public void testGetOneProduct() {
		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products/4";
		// call get one product api
		ResponseEntity<Product> response = testRestTemplate.getForEntity(url, Product.class);
		// test response

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(4, response.getBody().getId());
		assertEquals("Mac laptop xyz series", response.getBody().getName());
		assertEquals(64455.34, response.getBody().getPrice());
		assertEquals(" I is a wonderfull laptop.", response.getBody().getDescription());

	}

	@Test
	@DisplayName("Add One Product test")
	public void testAddProduct() {

		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products";

		// create a product
		Product product = new Product("Dell Gamming Laptop XYZ series", 98765.345, "It is a super gamming laptop");

		// create Http request entity obj
		HttpEntity<Product> requestObj = new HttpEntity<>(product);

		// call get one product api
		ResponseEntity<Product> response = testRestTemplate.postForEntity(url, requestObj, Product.class);

		// test response
		assertEquals(200, response.getStatusCodeValue());
		productId = response.getBody().getId();
		assertEquals("Dell Gamming Laptop XYZ series", response.getBody().getName());
		assertEquals(98765.345, response.getBody().getPrice());
		assertEquals("It is a super gamming laptop", response.getBody().getDescription());
	}

	@Test
	@DisplayName("Add One Product test for null value")
	public void testAddProductWithNull() {

		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products";

		// create a product
		Product product = null;

		// create Http request entity obj
		HttpEntity<Product> requestObj = new HttpEntity<>(product);

		// call get one product api
		ResponseEntity<Product> response = testRestTemplate.postForEntity(url, requestObj, Product.class);

		// test response
		// HTTP 415 Unsupported Media Type client
		assertEquals(415, response.getStatusCodeValue());

	}

	@Test
	@DisplayName("GET one product test with non existing productId")
	public void testGetOneProductWithNonExistingProductId() {
		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products/400";
		// call get one product api
		ResponseEntity<Product> response = testRestTemplate.getForEntity(url, Product.class);
		// test response
		assertEquals(500, response.getStatusCodeValue());
	}

	@Test
	@DisplayName("DELETE one Product test")
	public void testDeleteOneProduct() {
		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products/" + productId;
		// call get one product api
		testRestTemplate.delete(url);
	}
	
	
	@Test
	@DisplayName("Update One Product test")
	public void testUpdateProduct() {

		// formulate a test url
		String url = "http://localhost:" + randomPort + "/products/7";

		// create a product
		Product product = new Product("Lenovo Gamming Laptop XYZ series", 58765.345, "It is a super gamming laptop");

		// create Http request entity obj
		HttpEntity<Product> requestObj = new HttpEntity<>(product);

		// call get one product api
		ResponseEntity<Product> response = testRestTemplate.exchange(url,HttpMethod.PUT,requestObj, Product.class);

		// test response
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Lenovo Gamming Laptop XYZ series", response.getBody().getName());
		assertEquals(58765.345, response.getBody().getPrice());
		assertEquals("It is a super gamming laptop", response.getBody().getDescription());
	}

}
