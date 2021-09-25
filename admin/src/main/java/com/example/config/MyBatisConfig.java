package com.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: barry.jt.huang
 * @Date: 2020/11/13 0013
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.example.mapper","com.example.dao"})
public class MyBatisConfig {
}

