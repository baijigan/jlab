package com.ibeetl.dao.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibeetl.dao.common.TestServiceInterface;
import com.ibeetl.dao.jpa.entity.JpaUser;

public class JpaPerformaceTestService implements TestServiceInterface {

	
	int index = 1;
	
	@Autowired
	JpaUserService userService;
	
	@Override
	public void testAdd() {
		JpaUser user = this.getNewUser();
		userService.addUser(user);

	}

	@Override
	public void testUnique() {
		userService.unique(1);

	}

	@Override
	public void testUpdateById() {
		JpaUser user = this.getNewUser();
		user.setId(1);
		user.setCode("abc");
		userService.updateUser(user);

	}
	
	private JpaUser getNewUser() {
		JpaUser user = new JpaUser();
		user.setId(index++);
		user.setCode("abc");
		return user;
		
	}

	@Override
	public void testPageQuery() {
		userService.pageQuery("abc");
		
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
