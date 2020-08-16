package com.yan.dao;

import java.util.List;

import org.apache.catalina.ant.FindLeaksTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.naming.java.javaURLContextFactory;

import com.yan.domain.Role;
import com.yan.domain.UserInfo;

public interface UserDao {
	@Select("select * from users where username=#{username}")
	@Results({
			@Result(id = true,property = "id",column = "id"),
			@Result(property = "username",column = "username"),
			@Result(property = "email",column = "email"),
			@Result(property = "password",column = "password"),
			@Result(property = "phoneNum",column = "phoneNum"),
			@Result(property = "status",column = "status"),
			@Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yan.dao.RoleDao.findRoleByUserId")),
	})
	public UserInfo findByUsername(String username);

	@Select("select * from users")
	public List<UserInfo> findAll();
	
	@Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status}) ")
	public void save(UserInfo userInfo);

	@Select("select * from users where id=#{id}")
	@Results({
		@Result(id = true,property = "id",column = "id"),
		@Result(property = "username",column = "username"),
		@Result(property = "email",column = "email"),
		@Result(property = "password",column = "password"),
		@Result(property = "phoneNum",column = "phoneNum"),
		@Result(property = "status",column = "status"),
		@Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yan.dao.RoleDao.findRoleByUserId")),
	})
	public UserInfo findById(String id);

	@Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
	public List<Role> findOtherRoles(String userId);

	@Insert("insert into users_role values (#{userId},#{roleId})")
	public void addRoleToUser(@Param("userId")String userId, @Param("roleId")String roleId);
}
