package com.ibeetl.dao.jdbc;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibeetl.dao.beetlsql.entity.BeetlSqlUser;
import com.ibeetl.dao.common.TestServiceInterface;

public class RawJDBCPerformaceTestService implements TestServiceInterface {

    int index = 1;

    @Autowired
    UserDao dao;

    @Override
    public void testAdd() {
        BeetlSqlUser user = this.getNewUser();
        try {
            dao.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void testUnique() {
        try {
            dao.unqiue(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void testUpdateById() {
        BeetlSqlUser user = this.getNewUser();
        try {
            user.setId(1);
            user.setCode("hello");
            dao.updateById(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private BeetlSqlUser getNewUser() {
        BeetlSqlUser user = new BeetlSqlUser();
        user.setId(index++);
        user.setCode("abc");
        return user;
    }

    @Override
    public void testPageQuery() {
        
        try {
            dao.pageQuery("abc");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

      

    }

    @Override
    public void testExampleQuery() {
    	throw new UnsupportedOperationException();
    }

	@Override
	public void testOrmQUery() {
		throw new UnsupportedOperationException();
		
	}

}
