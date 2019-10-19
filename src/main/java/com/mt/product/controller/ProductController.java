package com.mt.product.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mt.product.exception.ProductNotFoundException;
import com.mt.product.exception.ResourceNotAvailableException;
import com.mt.product.model.Product;
import com.mt.product.service.ProductService;


@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "Retrieve the given product detail for the productId")
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> productLookup(@PathVariable Integer productId) {
		try{
			return ResponseEntity.ok().body(productService.findById(productId));
		}catch(Exception ex){
			throw new ProductNotFoundException("This product "+ productId +" id not found");
		}
	}
	
	@ApiOperation(value = "Retrieve the all products from data source")
	@GetMapping("/product")
	public ResponseEntity<List<Product>> allProduct() {
		try{
			return ResponseEntity.ok().body(productService.findAll());
		}catch(Exception ex){
			throw new ResourceNotAvailableException("Resource not found here");
		}
	}

}
