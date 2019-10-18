package com.mt.product.service;

import java.util.List;

import com.mt.product.model.Product;

public interface ProductService {
	public List<Product> findAll();
	public Product findById(int productId);
}
