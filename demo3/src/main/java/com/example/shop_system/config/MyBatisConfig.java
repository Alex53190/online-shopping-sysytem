package com.example.shop_system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


    @Configuration
    @MapperScan("com.example.shop_system.mapper")  // 扫描 Mapper 接口
    public class MyBatisConfig {
    }

