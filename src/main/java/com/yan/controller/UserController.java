package com.yan.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yan.domain.Role;
import com.yan.domain.UserInfo;
import com.yan.service.impl.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/addRoleToUser")
	//给用户添加角色
	public String   addRoleToUser (@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds) {
		userService.addRoleToUser(userId,roleIds);
		return "redirect:findAll";
	}
	
	//查询用户以及用户可以添加的角色
	@RequestMapping("/findUserByIdAndAllRole")
	public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) {
		ModelAndView mv = new ModelAndView();
		//1.根据id查询用户
		UserInfo userInfo = userService.findById(userId);
		//2.根据id查询用户可以添加的角色
		List<Role> otherRoles = userService.findOtherRoles(userId);
		mv.addObject("user",userInfo);
		mv.addObject("roleList",otherRoles);
		mv.setViewName("user-role-add");
		return mv;
	}
	
	@RequestMapping("/findById")
	public ModelAndView findById(String id) {
		ModelAndView mv = new ModelAndView();
		UserInfo userInfo = userService.findById(id);
		mv.addObject("user",userInfo);
		mv.setViewName("user-show1");
		return mv;
	}
	
	@RequestMapping("/save")
	public String save(UserInfo userInfo) {
		userService.save(userInfo);
		return "redirect:findAll"; 
	}
	@RequestMapping("/findAll")
	@PreAuthorize("hasRole('ROLE_VIP')")
	public ModelAndView findAll(){
		ModelAndView mv = new ModelAndView();
		List<UserInfo> userList = userService.findAll();
		mv.addObject("userList",userList);
		mv.setViewName("user-list");
		return mv;
	}
}
