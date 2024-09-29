package com.example.demo.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean; // 导入正确的类
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MyBatisPlusConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 使用MybatisSqlSessionFactoryBean而不是SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // 设置MyBatis-Plus的全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        DbConfig dbConfig = new DbConfig();
        dbConfig.setLogicDeleteField("deleted");
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");
        globalConfig.setDbConfig(dbConfig);
        factoryBean.setGlobalConfig(globalConfig);

        // 设置Mapper XML文件位置
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*.xml")
        );

        return factoryBean.getObject();
    }
}
