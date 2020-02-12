package com.ibeetl.dao.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.beetl.sql.core.annotatoin.Table;

@Entity()
@Table(name="sys_order")
public class JpaSqlOrder {
	@Id
	private Integer id;
	private String name;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cust_id")
	private JpaCustomer customer;

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

	public JpaCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(JpaCustomer customer) {
		this.customer = customer;
	}



}
