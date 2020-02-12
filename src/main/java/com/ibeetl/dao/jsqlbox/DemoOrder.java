package com.ibeetl.dao.jsqlbox;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.drinkjava2.jdialects.annotation.jdia.SingleFKey;
import com.github.drinkjava2.jsqlbox.ActiveEntity;

@Table(name = "sys_order")
public class DemoOrder implements ActiveEntity<DemoOrder> {
	@Id
	private Integer id;

	private String name;

	@Column(name = "cust_id")
	@SingleFKey(refs = { "sys_customer", "id" })
	private Integer custId;

	DemoCustomer demoCustomer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DemoCustomer getDemoCustomer() {
		return demoCustomer;
	}

	public void setDemoCustomer(DemoCustomer demoCustomer) {
		this.demoCustomer = demoCustomer;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

}
