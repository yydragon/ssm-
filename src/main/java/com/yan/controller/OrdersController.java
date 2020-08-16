package com.yan.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.github.pagehelper.PageInfo;
import com.yan.domain.Orders;
import com.yan.service.impl.OrderService;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private OrderService orderService;
	
	//查询全部订单---为分页
//	@RequestMapping("/findAll")	
//	public ModelAndView findAll() {
//		
//		ModelAndView mv = new ModelAndView();
//		List<Orders> ordersList = orderService.findAll();
//		mv.addObject("ordersList",ordersList);
//		mv.setViewName("orders-list");
//		return mv;
//	}
	
	
	@RequestMapping("/findAll")
	@Secured("ROLE_VIP")
	public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) {
		ModelAndView mv = new ModelAndView();
		List<Orders> ordersList = orderService.findAll(page,size);
		PageInfo pageInfo = new PageInfo(ordersList);
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("orders-page-list");
		return mv;
	}
	
		@RequestMapping("/findById")
		public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) {
			ModelAndView mv = new ModelAndView();
			Orders findById = orderService.findById(ordersId);
			mv.addObject("orders",findById);
			mv.setViewName("orders-show");
			return mv;
		}
}
