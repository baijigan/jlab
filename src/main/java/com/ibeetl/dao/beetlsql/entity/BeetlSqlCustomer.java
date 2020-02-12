package com.ibeetl.dao.beetlsql.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

@Table(name="sys_customer")
public class BeetlSqlCustomer {
	@AssignID
	private Integer id ;
	private String code ;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
