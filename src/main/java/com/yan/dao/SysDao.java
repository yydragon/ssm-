package com.yan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import com.yan.domain.SysLog;

public interface SysDao {
	@Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
	public void save(SysLog sysLog);

	@Select("select * from sysLog")
	public List<SysLog> findAll();
}
