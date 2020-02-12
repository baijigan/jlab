package com.ibeetl.dao.anima.entity;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "sys_user")
public class AnimaUser extends Model {
    private Integer id ;
    private String code ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
