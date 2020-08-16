package com.yan.service.impl;

import java.util.List;

import com.yan.domain.Product;

public interface ProductService {
	public List<Product> findAll();

	public void save(Product product);
	
}
