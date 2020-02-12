package com.ibeetl.dao.beetlsql.service;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibeetl.dao.beetlsql.entity.BeetlSqlUser;
import com.ibeetl.dao.common.TestServiceInterface;

public class BeetlSqlPerformaceTestService implements TestServiceInterface {

	
	int index = 1;
	
	@Autowired
	BeetlSqlUserService userService;
	
	@Override
	public void testAdd() {
		BeetlSqlUser user = this.getNewUser();
		userService.addUser(user);

	}

	@Override
	public void testUnique() {
		BeetlSqlUser user = userService.unique(1);
		

	}

	@Override
	public void testUpdateById() {
		BeetlSqlUser user = this.getNewUser();
		user.setId(1);
		user.setCode("abc");
		userService.updateUser(user);

	}
	
	private BeetlSqlUser getNewUser() {
		BeetlSqlUser user = new BeetlSqlUser();
		user.setId(index++);
		user.setCode("abc");
		return user;
		
	}

	@Override
	public void testPageQuery() {
		PageQuery query = new PageQuery(1,10);
		query.setPara("code", "abc");
		userService.query(query);
		
	}

	@Override
	public void testExampleQuery() {
		userService.example(1);
		
	}

	@Override
	public void testOrmQUery() {
		userService.ormQuery();
		
	}

}
