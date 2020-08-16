package com.yan.service.impl;

import java.util.List;

import com.yan.domain.Permission;
import com.yan.domain.Role;

public interface RoleService {
	public List<Role> findAll();

	public void save(Role role);

	public Role findById(String roleId);


	public void deleteRoleById(String roleId);

	public List<Permission> findOtherPermissions(String roleId);

	public void addPermissionToRole(String roleId, String[] permissionIds);

}
