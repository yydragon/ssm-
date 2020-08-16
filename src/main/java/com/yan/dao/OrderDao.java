package com.yan.dao;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import com.yan.domain.Member;
import com.yan.domain.Orders;
import com.yan.domain.Product;

public interface OrderDao {
	@Select("select * from orders")
	@Results({
		@org.apache.ibatis.annotations.Result(id=true,property = "id",column = "id"),
		@org.apache.ibatis.annotations.Result(property = "orderNum",column = "orderNum"),
		@org.apache.ibatis.annotations.Result(property = "orderTime",column = "orderTime"),
		@org.apache.ibatis.annotations.Result(property = "orderStatus",column = "orderStatus"),
		@org.apache.ibatis.annotations.Result(property = "peopleCount",column = "peopleCount"),
		@org.apache.ibatis.annotations.Result(property = "payType",column = "payType"),
		@org.apache.ibatis.annotations.Result(property = "orderDesc",column = "orderDesc"),
		@org.apache.ibatis.annotations.Result(property = "product",column = "productId",javaType = Product.class,one = @One(select="com.yan.dao.ProductDao.findById")),
	})
	public List<Orders> findAll();

	
	@Select("select * from orders where id=#{oordersId}")
	@Results({
		@org.apache.ibatis.annotations.Result(id=true,property = "id",column = "id"),
		@org.apache.ibatis.annotations.Result(property = "orderNum",column = "orderNum"),
		@org.apache.ibatis.annotations.Result(property = "orderTime",column = "orderTime"),
		@org.apache.ibatis.annotations.Result(property = "orderStatus",column = "orderStatus"),
		@org.apache.ibatis.annotations.Result(property = "peopleCount",column = "peopleCount"),
		@org.apache.ibatis.annotations.Result(property = "payType",column = "payType"),
		@org.apache.ibatis.annotations.Result(property = "orderDesc",column = "orderDesc"),
		@org.apache.ibatis.annotations.Result(property = "product",column = "productId",javaType = Product.class,one = @One(select="com.yan.dao.ProductDao.findById")),
		@org.apache.ibatis.annotations.Result(property = "member",column = "menberId",javaType = Member.class,one = @One(select = "com.yan.dao.MemberDao.findById")),
		@org.apache.ibatis.annotations.Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(select ="com.yan.dao.TravellerDao.findByOrdersId" ))
	})
	public Orders findById(String ordersId);
}
