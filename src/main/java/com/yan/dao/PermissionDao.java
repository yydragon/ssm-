package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yan.domain.Permission;

public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
	public List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values (#{permissionName},#{url})")
	public void save(Permission permission);
}
