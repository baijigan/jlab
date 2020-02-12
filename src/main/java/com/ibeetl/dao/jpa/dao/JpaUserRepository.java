package com.ibeetl.dao.jpa.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibeetl.dao.jpa.entity.JpaUser;
@Repository
public interface  JpaUserRepository extends JpaRepository<JpaUser, Integer>{

}
