package com.mt.product.model;

import com.opencsv.bean.CsvBindByName;


public class Product {
	
	@CsvBindByName(column = "ID", required = true)
	private int productId;
	@CsvBindByName(column = "NAME", required = true)
	private String productName;
	@CsvBindByName(column = "PRICE", required = true)
	private double price;
	@CsvBindByName(column = "OLD_PRICE", required = true)
	private double oldPrice;
	@CsvBindByName(column = "STOCK", required = true)
	private int stock;
	@CsvBindByName(column = "BRAND", required = true)
	private String brand;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}
