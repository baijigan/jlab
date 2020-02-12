package com.ibeetl.dao.common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibeetl.dao.anima.service.AnimaPerformaceTestService;
import com.ibeetl.dao.beetlsql.service.BeetlSqlPerformaceTestService;
import com.ibeetl.dao.jdbc.RawJDBCPerformaceTestService;
import com.ibeetl.dao.jpa.service.JpaPerformaceTestService;
import com.ibeetl.dao.jsqlbox.JSqlBoxPerformanceTestService;
import com.ibeetl.dao.mybatis.service.MyBatisPerformanceTestService;
import com.ibeetl.dao.think.service.ThinkPerformaceTestService;

import io.github.biezhi.anima.Anima;

@Configuration
public class TestServiceConfig {
    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "beetlsql")
    public TestServiceInterface getBeetlSqlTestService() {
        return new BeetlSqlPerformaceTestService();
    }


    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "jpa")
    public TestServiceInterface getJpaTestService() {
        return new JpaPerformaceTestService();
    }

    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "mybatis")
    public TestServiceInterface getMyBatisTestService() {
        return new MyBatisPerformanceTestService();
    }
    
    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "jdbc")
    public TestServiceInterface getRawJDBCTestService() {
        return new RawJDBCPerformaceTestService();
    }

    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "think")
    public TestServiceInterface getThinkTestService() {
        return new ThinkPerformaceTestService();
    }

    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "anima")
    public TestServiceInterface getAnimaTestService(@Autowired DataSource dataSource) {
        Anima.open(dataSource);
        return new AnimaPerformaceTestService();
    }
    
    @Bean
    @ConditionalOnProperty(name = "test.target", havingValue = "jsqlbox")
    public JSqlBoxPerformanceTestService getJSqlBoxTestService() {
        return new JSqlBoxPerformanceTestService();
    }
}
