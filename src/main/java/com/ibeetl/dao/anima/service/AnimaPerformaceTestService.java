package com.ibeetl.dao.anima.service;

import com.ibeetl.dao.anima.entity.AnimaUser;
import com.ibeetl.dao.common.TestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimaPerformaceTestService implements TestServiceInterface {

    int index = 1;

    @Autowired
    AnimaUserService userService;

    @Override
    public void testAdd() {
        AnimaUser user = this.getNewUser();
        userService.addUser(user);

    }

    @Override
    public void testUnique() {
        AnimaUser user = userService.unique(1);


    }

    @Override
    public void testUpdateById() {
        AnimaUser user = this.getNewUser();
        user.setId(1);
        user.setCode("abc");
        userService.updateUser(user);

    }

    private AnimaUser getNewUser() {
        AnimaUser user = new AnimaUser();
        user.setId(index++);
        user.setCode("abc");
        return user;

    }

    @Override
    public void testPageQuery() {
        userService.query("abc",1,10);
    }

    @Override
    public void testExampleQuery() {
        userService.example(1);

    }

	@Override
	public void testOrmQUery() {
		throw new UnsupportedOperationException();
		
	}
}
