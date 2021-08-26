package com.dell.webservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.dell.webservice.controller.HomeController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Main Test Application")
class Phase3EcommerceWebservice08172021ApplicationTests {
	
	@Autowired
	private HomeController controller;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;

	@Test
	@DisplayName("Test applicatiojn context load")
	void contextLoads() {
		assertNotNull(controller);
	}
	
	@Test
	@DisplayName("Test server is running")
	void testForRunningServer() {
		String url = "http://localhost:"+randomPort+"/";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		
		assertEquals("Spring application server is up and running!", response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test sample api is running")
	void testForSampleAPI() {
		String url = "http://localhost:"+randomPort+"/hello";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		
		assertEquals("Hello, to spring boot world", response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

}
