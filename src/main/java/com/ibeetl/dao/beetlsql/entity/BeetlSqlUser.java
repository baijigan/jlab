package com.ibeetl.dao.beetlsql.entity;
import java.io.Serializable;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

@Table(name="sys_user")
public class BeetlSqlUser  extends TailBean implements Serializable{
	@AssignID
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
