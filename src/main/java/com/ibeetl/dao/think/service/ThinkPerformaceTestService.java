package com.ibeetl.dao.think.service;

import com.ibeetl.dao.common.TestServiceInterface;
import com.ibeetl.dao.think.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>@Description: 测试模板</p>
 *
 * @author Sue
 * @date 2018/6/4下午4:56
 */
public class ThinkPerformaceTestService implements TestServiceInterface {

    int index = 1;

    @Autowired
    private ThinkUserService thinkUserService;

    @Override
    public void testAdd() {
        User user = this.getNewUser();
        thinkUserService.addUser(user);
    }

    @Override
    public void testUnique() {
        thinkUserService.unique(1);
    }

    @Override
    public void testUpdateById() {
        User user = new User(1, "abc");
        thinkUserService.updateById(user);
    }

    @Override
    public void testPageQuery() {
        thinkUserService.pageQuery(0, 10);
    }

    @Override
    public void testExampleQuery() {
        thinkUserService.example(1);
    }

    /**
     * 获取一个新对象：ID自增
     * @return
     */
    private User getNewUser() {
        User user = new User(index++, "abc");
        return user;

    }

	@Override
	public void testOrmQUery() {
		// TODO Auto-generated method stub
		
	}
}
