package com.yan.dao;

import org.apache.ibatis.annotations.Select;

import com.yan.domain.Member;

public interface MemberDao {
	@Select("select * from member where id= #{id}")
	public Member findByid(String id);
}
