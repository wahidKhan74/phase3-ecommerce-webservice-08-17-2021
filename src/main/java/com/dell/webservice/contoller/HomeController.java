package com.dell.webservice.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String indexMapper() {
		return "Spring application server is up and running!";
	}
	
	@GetMapping("/hello")
	public String helloMapper() {
		return "Hello, to spring boot world";
	}
}
