package com.example.co_templates.configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
// @MapperScan(basePackages = "com.example.co_templates.daos") // 여기에는 MyBatis 매퍼 인터페이스가 위치한 패키지를 지정합니다.
public class MyBatisConfig {

    // @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}