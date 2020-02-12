package com.ibeetl.dao.jpa.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.ibeetl.dao.jpa.dao.JpaOrderRepository;
import com.ibeetl.dao.jpa.dao.JpaUserRepository;
import com.ibeetl.dao.jpa.entity.JpaCustomer;
import com.ibeetl.dao.jpa.entity.JpaSqlOrder;
import com.ibeetl.dao.jpa.entity.JpaUser;
import com.ibeetl.dao.jpa.service.JpaUserService;
@Service
public class JpaUserServiceImpl implements JpaUserService {
	@Autowired
	JpaUserRepository userDao;
	
	@Autowired
	JpaOrderRepository orderDao;
	
	@Autowired
	EntityManager em;
	
	@Override
	public void addUser(JpaUser user) {
		userDao.save(user);

	}

	@Override
	public JpaUser unique(Integer id) {
		return userDao.findById(id).get();
	}

	@Override
	public void updateUser(JpaUser user) {
		userDao.save(user);

	}

	@Override
	public void pageQuery(String code) {
		
		
		long count = getCount(code);
		StringBuilder sql = new StringBuilder("select * from sys_user where 1=1");
		if(StringUtils.isNotEmpty(code)) {
			sql.append(" and code=?");
		}
		Query query = em.createNativeQuery(sql.toString(), JpaUser.class);
		query.setFirstResult(1);
		query.setMaxResults(10);
		if(StringUtils.isNotEmpty(code)) {
			query.setParameter(1, code);
		}
		
		List list = query.getResultList();
		
	}
	
	private long getCount(String code) {
		StringBuilder sql = new StringBuilder("select count(1) from sys_user where 1=1");
		if(StringUtils.isNotEmpty(code)) {
			sql.append(" and code=?");
		}
		Query query = em.createNativeQuery(sql.toString());
		if(StringUtils.isNotEmpty(code)) {
			query.setParameter(1, code);
		}
		return ((Number)query.getSingleResult()).longValue();
	}

	@Override
	public void example(Integer id) {
		
//		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());
		JpaUser user = new JpaUser();
		user.setId(id);
		Example<JpaUser> example = Example.of(user);
		Pageable pageRequest = PageRequest.of(0, 1);
		Page<JpaUser> page= userDao.findAll(example, pageRequest);
		if(page.getContent().size()!=1) {
			throw new  RuntimeException("wrong result");
		}
	}
	
	@Override
	public void ormQuery() {
		List<JpaSqlOrder> list = orderDao.findAll();
		for(JpaSqlOrder order:list) {
			JpaCustomer customer = order.getCustomer();
			if(customer==null) {
				throw new RuntimeException("orm error");
			}
		}
	}

	
}
