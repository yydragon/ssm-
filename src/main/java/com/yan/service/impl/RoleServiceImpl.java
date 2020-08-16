package com.yan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yan.dao.RoleDao;
import com.yan.domain.Permission;
import com.yan.domain.Role;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		// TODO 自动生成的方法存根
		return roleDao.findAll();
	}

	@Override
	public void save(Role role) {
		// TODO 自动生成的方法存根
		roleDao.save(role);
	}

	@Override
	public Role findById(String roleId) {
		// TODO 自动生成的方法存根
		return roleDao.findById(roleId);
	}

	@Override
	public void deleteRoleById(String roleId) {
		// TODO 自动生成的方法存根
		roleDao.deleteFromUser_RoleByRoleId(roleId);
		roleDao.deleteFromRole_PermissionByRoleId(roleId);
		roleDao.deleteRoleById(roleId);
	}

	@Override
	public List<Permission> findOtherPermissions(String roleId) {
		// TODO 自动生成的方法存根
		return roleDao.findOtherPermissions(roleId);
	}

	@Override
	public void addPermissionToRole(String roleId, String[] permissionIds) {
		// TODO 自动生成的方法存根
		for(String permissionId:permissionIds) {
			roleDao.addPermissionToRole(roleId,permissionId);
		}
	}



}
