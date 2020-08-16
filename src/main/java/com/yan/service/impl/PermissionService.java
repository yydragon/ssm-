package com.yan.service.impl;

import java.util.List;

import com.yan.domain.Permission;

public interface PermissionService {
	public List<Permission> findAll();

	public void save(Permission permission);
}
