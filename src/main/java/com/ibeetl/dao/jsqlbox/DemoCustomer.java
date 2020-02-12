package com.ibeetl.dao.jsqlbox;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_customer")
public class DemoCustomer {
	@Id
	private Integer id;

	private String code;

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
