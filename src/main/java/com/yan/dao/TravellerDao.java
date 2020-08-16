package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yan.domain.Traveller;

public interface TravellerDao {
	@Select("SELECT * from traveller where id in(SELECT travellerId from order_traveller where orderId=#{ordersId})")
	public List<Traveller> findByOrdersId(String ordersId);
}
