package com.ibeetl.dao.beetlsql.service;

import org.beetl.sql.core.engine.PageQuery;

import com.ibeetl.dao.beetlsql.entity.BeetlSqlUser;

public interface BeetlSqlUserService {
	public void addUser(BeetlSqlUser user);
	public BeetlSqlUser unique(Integer id);
	public void updateUser(BeetlSqlUser user);
	public void query(PageQuery query);
	public void example(Integer id);
	public void ormQuery();
	
}
