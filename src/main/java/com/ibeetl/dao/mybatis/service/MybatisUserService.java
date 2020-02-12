package com.ibeetl.dao.mybatis.service;

import com.baomidou.mybatisplus.service.IService;
import com.ibeetl.dao.mybatis.entity.SysUser;

/**
 * MyBatis用户实体业务接口
 * <p>
 * create by 叶云轩 at 2018/5/23-上午9:26
 * contact by tdg_yyx@foxmail.com
 */
public interface MybatisUserService extends IService<SysUser> {

    void addUser(SysUser user);

    SysUser unique(Integer id);

    void updateUser(SysUser user);

    void pageQuery(String code);

    void example(Integer id);
}
