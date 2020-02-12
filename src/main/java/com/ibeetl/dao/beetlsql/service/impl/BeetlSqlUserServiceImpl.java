package com.ibeetl.dao.beetlsql.service.impl;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibeetl.dao.beetlsql.dao.BeetlSqlOrderDao;
import com.ibeetl.dao.beetlsql.dao.BeetlSqlUserDao;
import com.ibeetl.dao.beetlsql.entity.BeetlSqlCustomer;
import com.ibeetl.dao.beetlsql.entity.BeetlSqlOrder;
import com.ibeetl.dao.beetlsql.entity.BeetlSqlUser;
import com.ibeetl.dao.beetlsql.service.BeetlSqlUserService;

@Service
public class BeetlSqlUserServiceImpl implements BeetlSqlUserService {	
	@Autowired 
	BeetlSqlUserDao userDao;
	@Autowired 
	BeetlSqlOrderDao orderDao;

	@Override
	public void addUser(BeetlSqlUser user) {
		userDao.insert(user);
		
	}

	@Override
	public BeetlSqlUser unique(Integer id) {
		return userDao.unique(id);
	}

	@Override
	public void updateUser(BeetlSqlUser user) {
		 userDao.updateById(user);
		
	}

	@Override
	public void query(PageQuery query) {
		userDao.selectUser(query);
		
	}

	@Override
	public void example(Integer  id) {
		BeetlSqlUser user = userDao.createLambdaQuery()
				.andEq(BeetlSqlUser::getId, id).single();
		
	}

	@Override
	public void ormQuery() {
		List<BeetlSqlOrder> list = orderDao.all();
		for(BeetlSqlOrder order:list) {
			BeetlSqlCustomer customer = (BeetlSqlCustomer) order.get("beetlSqlCustomer");
			if(customer==null) {
				throw new RuntimeException("orm error");
			}
		}
		
	}
	
}
