package com.yan.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yan.domain.Role;
import com.yan.domain.UserInfo;

public interface UserService extends UserDetailsService {

	List<UserInfo> findAll();
	
	void save(UserInfo userInfo);

	UserInfo findById(String id);

	List<Role> findOtherRoles(String userId);

	void addRoleToUser(String userId, String[] roleIds);
	
}
