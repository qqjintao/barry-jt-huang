package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author barry.jt.huang
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("===================");
        System.out.println("======启动成功======");
        System.out.println("===================");
    }
}
