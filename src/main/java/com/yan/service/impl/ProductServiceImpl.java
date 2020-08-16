package com.yan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yan.dao.ProductDao;
import com.yan.domain.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findAll() {
		// TODO 自动生成的方法存根
		return productDao.findAll();
	}

	@Override
	public void save(Product product) {
		// TODO 自动生成的方法存根
		productDao.save(product);
	}

}
