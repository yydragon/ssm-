package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yan.domain.Permission;
import com.yan.domain.Role;

public interface RoleDao {

	//根据id查询出所有对应的角色
	@Select(" select * from role where id in (select roleId from users_role where userId=#{userId})")
	@Results({
		@Result(id = true,property = "id",column = "id"),
		@Result(property = "roleName",column = "roleName"),
		@Result(property = "roleDesc",column = "roleDesc"),
		@Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yan.dao.PermissionDao.findPermissionByRoleId")),
	})
	public List<Role> findRoleByUserId(String userId);

	@Select("select * from role")
	public List<Role> findAll();

	@Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
	public void save(Role role);


	@Select("select * from role where id=#{roleId}")
	@Results({
		@Result(id = true,property = "id",column = "id"),
		@Result(property = "roleName",column = "roleName"),
		@Result(property = "roleDesc",column = "roleDesc"),
		@Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yan.dao.PermissionDao.findPermissionByRoleId")),
	})
	public Role findById(String roleId);


	@Delete("delete from role_permission where roleId=#{roleId}")
	public void deleteFromRole_PermissionByRoleId(String roleId);

	@Delete("delete from role where id=#{roleId}")
	public void deleteRoleById(String roleId);

	@Delete("delete from users_role where roleId=#{roleId}")
	public void deleteFromUser_RoleByRoleId(String roleId);

	@Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
	public List<Permission> findOtherPermissions(String roleId);

	@Insert("insert into role_permission(roleId,permissionId) values (#{roleId},#{permissionId})")
	public void addPermissionToRole(@Param("roleId")String roleId, @Param("permissionId")String permissionId);
}
