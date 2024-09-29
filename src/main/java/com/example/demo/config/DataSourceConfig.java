package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/security_manager?serverTimezone=GMT%2B8&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        // 设置其他属性
        return dataSource;
    }
}
