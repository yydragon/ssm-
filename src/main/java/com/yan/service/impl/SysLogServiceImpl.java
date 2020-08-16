package com.yan.service.impl;

import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yan.dao.SysDao;
import com.yan.domain.SysLog;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
	@Autowired
	private SysDao sysDao;
	@Override
	public void save(SysLog sysLog) {
		sysDao.save(sysLog);
		
	}
	@Override
	public List<SysLog> findAll() {
		// TODO 自动生成的方法存根
		return sysDao.findAll();
	}

}
