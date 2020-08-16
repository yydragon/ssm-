package com.yan.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.catalina.authenticator.SavedRequest;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yan.domain.Product;
import com.yan.service.impl.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	//产品添加
	@RequestMapping("/save")
	public String  save(Product product) {
		productService.save(product);
		return "redirect:findAll";
	}
	
	//查询全部产品
	@RequestMapping("/findAll")
	@RolesAllowed("VIP")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		List<Product> ps = productService.findAll();
		mv.addObject("productList",ps);
		mv.setViewName("product-list1");
		return mv;
		
	}
}
