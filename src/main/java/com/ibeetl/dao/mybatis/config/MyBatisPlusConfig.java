package com.ibeetl.dao.mybatis.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.ibeetl.dao.mybatis.mapper.MyBatisUserRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = {MyBatisUserRepository.class})
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
