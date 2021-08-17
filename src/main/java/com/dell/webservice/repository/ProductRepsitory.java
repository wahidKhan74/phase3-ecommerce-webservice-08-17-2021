package com.dell.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.webservice.entity.Product;

public interface ProductRepsitory extends JpaRepository<Product, Integer>{

}
