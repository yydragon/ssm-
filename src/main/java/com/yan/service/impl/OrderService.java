package com.yan.service.impl;

import java.util.List;

import com.yan.domain.Orders;

public interface OrderService {
	List<Orders> findAll(int page,int size);

	Orders findById(String ordersId);
}	
