package com.ibeetl.dao.jpa.service;

import com.ibeetl.dao.jpa.entity.JpaUser;

public interface JpaUserService {
	public void addUser(JpaUser user);
	public JpaUser unique(Integer id);
	public void updateUser(JpaUser user);
	public void pageQuery(String code);
	public void example(Integer id);
	public void ormQuery();
}
