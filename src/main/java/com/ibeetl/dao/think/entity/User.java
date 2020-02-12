package com.ibeetl.dao.think.entity;

import com.llqqww.thinkjdbc.Column;

import java.io.Serializable;

/**
 * <p>@Description: 实体对象</p>
 *
 * @author Sue
 * @date 2018/6/4下午4:46
 */
public class User implements Serializable {

    @Column(isKey=true, name = "ID", isAutoInc = false)
    private Integer id ;
    @Column(name = "CODE")
    private String code ;

    public User() {
    }

    public User(Integer id, String code) {
        this.id = id;
        this.code = code;
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
