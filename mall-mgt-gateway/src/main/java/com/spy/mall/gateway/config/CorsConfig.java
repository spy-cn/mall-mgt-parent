package com.spy.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 跨域的配置类
 * 只要创建自定义的跨域过滤器对象，就会注入到容器中，Spring就会按照我们自己的规则处理跨域请求
 *
 * @Author: spy
 * @Date: 2021/5/15 15:47
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        //配置自定义的跨域请求参数
        CorsConfiguration config = new CorsConfiguration();
        //允许跨域的域名，可以写成*，*代表所有域名，但是不能携带cookie
        config.addAllowedOrigin("http://www.mall.com");
        //是否允许携带cookie
        config.setAllowCredentials(true);
        //允许的请求源
        config.addAllowedOrigin("*");
        //允许的请求头
        config.addAllowedHeader("*");
        //允许的http方法
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}


