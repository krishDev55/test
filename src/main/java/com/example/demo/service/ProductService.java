package com.example.demo.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.AllProducts;
import com.example.demo.entity.vo.Products;

@Service
public class ProductService {

	
		@Autowired
		AllProducts allProduct;
	
	
	
	public Products getProduct() {
		int nextInt = new Random().nextInt(1000);
	
		List<Products> getAllProducts = new AllProducts().GetAllProducts();
		int size = getAllProducts.size();
		int value=nextInt % (size);
		
		return getAllProducts.get(value);
	}
}
