package com.yan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yan.domain.Permission;
import com.yan.domain.Role;
import com.yan.service.impl.RoleService;
import com.yan.service.impl.UserService;

@RequestMapping("/role")
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		List<Role> roleList = roleService.findAll();
		mv.addObject("roleList",roleList);
		mv.setViewName("role-list");
		return mv;
	}
	
	@RequestMapping("/save")
	public String  save(Role role) {
		roleService.save(role);
		return "redirect:findAll";
	}
	
	@RequestMapping("/findById")
	public ModelAndView findById(@RequestParam(name = "id",required = true)String roleId) {
		ModelAndView mv = new ModelAndView();
		Role role = roleService.findById(roleId);
		mv.addObject("role",role);
		mv.setViewName("role-show");
		return mv;
	}
	
	
	@RequestMapping("/deleteRole")
	public String  delete(@RequestParam(name = "id",required = true)String roleId) {
		roleService.deleteRoleById(roleId);
		return "redirect:findAll";
	}
	
	//根据roleId查询role，并查询出可以添加权限
	@RequestMapping("/findRoleByIdAndAllPermission")
	public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id")String roleId) {
		ModelAndView mv = new ModelAndView();
		//1.根据roleId查询Role
		Role role = roleService.findById(roleId);
		//2.根据RoleId可以添加的查询权限
		List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
		mv.addObject("role",role);
		mv.addObject("permissionList",otherPermissions);
		mv.setViewName("role-permission-add");
		return mv;
	}
	
	@RequestMapping("/addPermissionToRole")
	public String  addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds) {
		roleService.addPermissionToRole(roleId,permissionIds);
		return "redirect:findAll";
	}
}
