package com.ibeetl.dao.common;

import java.util.Map;
import java.util.TreeMap;

public class DaoResult {
	String daoName;
	Map<String,Integer> ret = new TreeMap<String,Integer>();
	public String getDaoName() {
		return daoName;
	}
	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	public Map<String, Integer> getRet() {
		return ret;
	}
	public void setRet(Map<String, Integer> ret) {
		this.ret = ret;
	}
	
}
