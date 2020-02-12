package com.ibeetl.dao.anima.service.impl;

import com.ibeetl.dao.anima.entity.AnimaUser;
import com.ibeetl.dao.anima.service.AnimaUserService;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;

import static io.github.biezhi.anima.Anima.select;

@Service
public class AnimaUserServiceImpl implements AnimaUserService {
    @Override
    public void addUser(AnimaUser user) {
        user.save();
    }

    @Override
    public AnimaUser unique(Integer id) {
        return select().from(AnimaUser.class).byId(id);
    }

    @Override
    public void updateUser(AnimaUser user) {
        user.updateById(user.getId());
    }

    @Override
    public Page<AnimaUser> query(String code,Integer page,Integer pageSize) {
        return select().from(AnimaUser.class).where(AnimaUser::getCode,code).page(page,pageSize);
    }

    @Override
    public void example(Integer id) {
        select().from(AnimaUser.class).where(AnimaUser::getId,id).one();
    }
}
