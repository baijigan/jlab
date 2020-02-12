package com.ibeetl.dao.think.service;

import com.ibeetl.dao.think.entity.User;

/**
 * <p>@Description: think需要实现的方法接口</p>
 *
 * @author Sue
 * @date 2018/6/4下午4:56
 */
public interface ThinkUserService {

    /**
     * 添加新用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据ID查询对象
     * @param id
     */
    void unique(int id);

    /**
     * 根据ID更新信息
     * @param user
     */
    void updateById(User user);

    /**
     * 根据ID查询数据
     * @param id
     */
    void example(int id);

    /**
     * 分页查询
     * @param limit
     * @param offset
     */
    void pageQuery(int limit, int offset);
}
