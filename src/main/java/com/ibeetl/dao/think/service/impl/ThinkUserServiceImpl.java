package com.ibeetl.dao.think.service.impl;

import com.ibeetl.dao.think.entity.User;
import com.ibeetl.dao.think.service.ThinkUserService;
import com.llqqww.thinkjdbc.D;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * <p>@Description: think实现</p>
 *
 * @author Sue
 * @date 2018/6/4下午5:03
 */
@Service
public class ThinkUserServiceImpl implements ThinkUserService {

    private static final String TABLE_NAME = "user";

    @Override
    public void addUser(User user) {
        try {
            D.M(user).add();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unique(int id) {
        try {
           User user =  D.M(User.class).find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(User user) {
        try {
            D.M(user).save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void example(int id) {
       
    }

    @Override
    public void pageQuery(int limit, int offset) {
        try {
            D.M(User.class).where("CODE=?", "abc").page(limit, offset).select();
            D.M(User.class).where("CODE=?", "abc").count();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
