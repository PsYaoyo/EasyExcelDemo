package com.yps.exceldemo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ExcelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelDemoApplication.class, args);
    }

}
