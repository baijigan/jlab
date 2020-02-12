package com.ibeetl.dao.mybatis.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = -7282685248525811969L;
    private Integer id;
    private String code;

    /**
     * 指定主键
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

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
