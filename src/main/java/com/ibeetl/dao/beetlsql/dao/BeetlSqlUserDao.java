package com.ibeetl.dao.beetlsql.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.ibeetl.dao.beetlsql.entity.BeetlSqlUser;

@SqlResource("user")
public interface BeetlSqlUserDao extends BaseMapper<BeetlSqlUser> {
	public void selectUser(PageQuery page);
	public BeetlSqlUser findUserAndDepartment(@Param("id") Integer id);
}