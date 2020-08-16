package com.yan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yan.domain.Permission;
import com.yan.service.impl.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		List<Permission> permissionList = permissionService.findAll();
		mv.addObject("permissionList",permissionList);
		mv.setViewName("permission-list");
		return mv;
	}
	
	@RequestMapping("/save")
	public String save(Permission permission) {
		permissionService.save(permission);
		return "redirect:findAll";
	}
}
