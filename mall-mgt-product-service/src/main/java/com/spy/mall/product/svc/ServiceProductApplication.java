package com.spy.mall.product.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:22
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.spy.mall")
public class ServiceProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class, args);
    }
}
