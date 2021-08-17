package com.dell.webservice.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dell.webservice.entity.Product;
import com.dell.webservice.exception.InvalidProductException;
import com.dell.webservice.exception.ProductAlreadyExistException;
import com.dell.webservice.exception.ProductNotFoundException;
import com.dell.webservice.repository.ProductRepsitory;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepsitory productRepsitory;

	// crud operations for product.

	// get one product
	@GetMapping("/products/{id}")
	public Product getOneProduct(@PathVariable("id") int id) {
		return this.productRepsitory.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException("Product does not exist with id " + id);
		});
	}

	// get all product
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		List<Product> products = productRepsitory.findAll();
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Product list is  empty !");
		}
		return products;
	}

	// create a product
	@PostMapping("/products")
	public Product addProduct(@RequestBody(required = false) Product productObj) {
		if (productObj == null) {
			throw new InvalidProductException("Product body cannot be empty !");
		}
		return this.productRepsitory.save(productObj);
	}

	// update product
	@PutMapping("/products/{id}")
	public Product updateOneProduct(@PathVariable("id") int id, @RequestBody(required = false) Product productObj) {
		
		this.productRepsitory.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException("Product does not exist with id " + id);
		});
		
		return this.productRepsitory.save(productObj);
	}

	// delete product
	@DeleteMapping("/products/{id}")
	public void deleteOneProduct(@PathVariable("id") int id) {
		Product fetchedProduct = this.productRepsitory.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException("Product does not exist with id " + id);
		});
		this.productRepsitory.delete(fetchedProduct);
	}

}
