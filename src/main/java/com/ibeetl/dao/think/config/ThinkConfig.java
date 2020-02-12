package com.ibeetl.dao.think.config;

import com.llqqww.thinkjdbc.D;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p>@Description: 初始化配置</p>
 *
 * @author Sue
 * @date 2018/6/4下午4:48
 */
@Configuration
public class ThinkConfig {

    @Resource(name = "datasource")
    private DataSource dataSource;

    @Bean
    public D config(@Qualifier(value = "datasource") DataSource dataSource) {
        D.setDataSource(dataSource);
        D.setTablePrefix("sys_");

        return new D();
    }
}
