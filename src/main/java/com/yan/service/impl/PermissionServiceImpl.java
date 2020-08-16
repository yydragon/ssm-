package com.yan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.dao.PermissionDao;
import com.yan.domain.Permission;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionDao permissionDao;
	@Override
	public List<Permission> findAll() {
		// TODO 自动生成的方法存根
		return permissionDao.findAll();
	}
	@Override
	public void save(Permission permission) {
		// TODO 自动生成的方法存根
		permissionDao.save(permission);
	}
	
}
