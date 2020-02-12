package com.ibeetl.dao.anima.service;

import com.ibeetl.dao.anima.entity.AnimaUser;
import io.github.biezhi.anima.page.Page;

public interface AnimaUserService {

    public void addUser(AnimaUser user);
    public AnimaUser unique(Integer id);
    public void updateUser(AnimaUser user);
    public Page<AnimaUser> query(String code, Integer page, Integer pageSize);
    public void example(Integer id);
}
