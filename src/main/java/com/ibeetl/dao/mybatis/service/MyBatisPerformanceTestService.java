package com.ibeetl.dao.mybatis.service;

import com.ibeetl.dao.common.TestServiceInterface;
import com.ibeetl.dao.mybatis.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MyBatisPerformance测试业务接口
 * <p>
 * create by 叶云轩 at 2018/5/23-上午9:29
 * contact by tdg_yyx@foxmail.com
 */
public class MyBatisPerformanceTestService implements TestServiceInterface {

    int index = 1;

    @Autowired
    private MybatisUserService mybatisUserService;

    @Override
    public void testAdd() {
        mybatisUserService.addUser(this.getInstance());
    }

    @Override
    public void testUnique() {
        mybatisUserService.unique(1);
    }

    @Override
    public void testUpdateById() {
        SysUser user = this.getInstance();
        user.setId(1);
        user.setCode("yyx");
        mybatisUserService.updateUser(user);

    }

    @Override
    public void testPageQuery() {
        mybatisUserService.pageQuery("yyx");
    }

    @Override
    public void testExampleQuery() {
        mybatisUserService.example(1);
    }

    private SysUser getInstance() {
        SysUser sysUser = new SysUser();
        sysUser.setCode("yyx");
        sysUser.setId(index++);
        return sysUser;
    }

	@Override
	public void testOrmQUery() {
		throw new UnsupportedOperationException();
		
	}
}
