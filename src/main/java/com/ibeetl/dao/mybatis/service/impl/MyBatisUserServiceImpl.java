package com.ibeetl.dao.mybatis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ibeetl.dao.mybatis.entity.SysUser;
import com.ibeetl.dao.mybatis.mapper.MyBatisUserRepository;
import com.ibeetl.dao.mybatis.service.MybatisUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

/**
 * <p>
 * create by 叶云轩 at 2018/5/23-上午9:37
 * contact by tdg_yyx@foxmail.com
 */
@Service
public class MyBatisUserServiceImpl
        extends ServiceImpl<MyBatisUserRepository, SysUser>
        implements MybatisUserService {


    @Override
    public void addUser(SysUser user) {
        baseMapper.insertAllColumn(user);
    }

    @Override
    public SysUser unique(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void updateUser(SysUser user) {
        baseMapper.updateById(user);
    }

    @Override
    public void pageQuery(String code) {
        EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
        SysUser sysUser = new SysUser();
        sysUser.setCode(code);
        entityWrapper.setEntity(sysUser);
        RowBounds rowBounds = new RowBounds(1, 10);
        baseMapper.selectPage(rowBounds, entityWrapper);
    }

    @Override
    public void example(Integer id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        baseMapper.selectOne(sysUser);
    }
}
