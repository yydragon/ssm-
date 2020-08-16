package com.yan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yan.dao.OrderDao;
import com.yan.domain.Orders;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Orders> findAll(int page,int size) {
		// TODO 自动生成的方法存根
		
		PageHelper.startPage(page,size);
		return orderDao.findAll();
	}

	@Override
	public Orders findById(String ordersId) {
		// TODO 自动生成的方法存根
		return orderDao.findById(ordersId);
	}

}
