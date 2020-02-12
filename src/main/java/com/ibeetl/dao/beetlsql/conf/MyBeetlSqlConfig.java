package com.ibeetl.dao.beetlsql.conf;

import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.H2Style;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibeetl.starter.BeetlSqlCustomize;

@Configuration
public class MyBeetlSqlConfig {
	@Bean
	public BeetlSqlCustomize beetlSqlCustomize() {
		return new BeetlSqlCustomize() {
			public void customize(SqlManagerFactoryBean sqlManagerFactoryBean) {
				sqlManagerFactoryBean.setNc(new UnderlinedNameConversion());
				sqlManagerFactoryBean.setDbStyle(new H2Style());
			}
		};
	}
}
