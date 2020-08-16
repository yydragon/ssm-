package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yan.domain.Product;

public interface ProductDao {
	
	//根据id查询产品
	@Select("select * from product where id=#{id}")
	public Product findById(String id);
	
	@Select("select * from product")
	public List<Product> findAll();
	
	@Select("insert into product(productNum,productName,cityName,DepartureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
	public void save(Product product);
}
