package com.yan.service.impl;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yan.dao.UserDao;
import com.yan.domain.Role;
import com.yan.domain.UserInfo;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自动生成的方法存根
		UserInfo userInfo=null;
		try {
			userInfo = userDao.findByUsername(username);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//处理自己的user对象封装成UserDetails
		//User user = new User(userInfo.getUsername(),userInfo.getPassword(),getAuthority());
		User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles())) ;
		return user;
	}

	public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		for(Role role:roles) {
			list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
			
		}
		
		return list;
	}

	@Override
	public List<UserInfo> findAll() {
		// TODO 自动生成的方法存根
		return userDao.findAll();
	}

	@Override
	public void save(UserInfo userInfo) {
		// TODO 自动生成的方法存根
		//对密码进行加密处理
		//userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
		userDao.save(userInfo);
	}

	@Override
	public UserInfo findById(String id) {
		// TODO 自动生成的方法存根
		return userDao.findById(id);
	}

	@Override
	public List<Role> findOtherRoles(String userId) {
		// TODO 自动生成的方法存根
		return userDao.findOtherRoles(userId);
	}

	@Override
	public void addRoleToUser(String userId, String[] roleIds) {
		// TODO 自动生成的方法存根
		for(String roleId:roleIds) {
			userDao.addRoleToUser(userId,roleId);	
		}
		
	}
}
