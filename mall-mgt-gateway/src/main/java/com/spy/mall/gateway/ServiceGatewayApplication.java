package com.spy.mall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author: spy
 * @Date: 2021/5/15 15:46
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }
}
