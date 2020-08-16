package com.yan.service.impl;

import java.util.List;

import com.yan.domain.SysLog;

public interface SysLogService {
	public void save(SysLog sysLog);

	public List<SysLog> findAll();
}
