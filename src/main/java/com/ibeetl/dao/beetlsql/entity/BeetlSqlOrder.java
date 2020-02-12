package com.ibeetl.dao.beetlsql.entity;

import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;

@OrmQuery(value = {
		@OrmCondition(target = BeetlSqlCustomer.class, attr = "custId", targetAttr = "id", type = OrmQuery.Type.ONE, lazy = false) })
@Table(name = "sys_order")
public class BeetlSqlOrder extends TailBean {
	@AssignID
	private Integer id;
	private String name;
	private Integer custId;

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

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

}
