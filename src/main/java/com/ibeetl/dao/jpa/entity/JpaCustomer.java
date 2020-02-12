package com.ibeetl.dao.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.beetl.sql.core.annotatoin.Table;

@Entity()
@Table(name="sys_customer")
public class JpaCustomer {
	@Id
	private Integer id ;
	private String code ;
	private String name;
	@OneToMany(mappedBy="customer")
	List<JpaSqlOrder> orders;
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
	public List<JpaSqlOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<JpaSqlOrder> orders) {
		this.orders = orders;
	}
	
}
