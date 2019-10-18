package com.mt.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.product.exception.ProductNotFoundException;
import com.mt.product.exception.ResourceNotAvailableException;
import com.mt.product.model.Product;
import com.mt.product.repositoty.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepo repository;

	private List<Product> products;

	public ProductServiceImpl(){
		products= new ArrayList<Product>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mt.product.service.ProductService#findAll()
	 * This finds the all product in data source and deliver to controller
	 */
	@Override
	public List<Product> findAll() {
		products = repository.allProducts();
		LOGGER.debug("Product list size = "+products.size());
		return Optional.ofNullable(products)
		.filter(n -> !n.isEmpty())
		.map(l -> l)
		.orElseThrow(() -> new ResourceNotAvailableException("Data not found"));
	}
	/*
	 * (non-Javadoc)
	 * @see com.mt.product.service.ProductService#findById(int)
	 * Find the product for specific productid
	 */
	@Override
	public Product findById(int productId) {
		products = repository.allProducts();
		Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(Product::getProductId, product -> product ,(oldProductId, newProductId) -> newProductId));
		return Optional.ofNullable(productMap)
				.filter(value -> !value.isEmpty())
				.map(p -> p.get(productId))
				.orElseThrow(() -> new ProductNotFoundException("This product id "+productId+" not found ."));

	}
}
