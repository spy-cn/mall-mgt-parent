package com.spy.mall.gateway.filters;

import com.spy.mall.common.utils.IpUtils;
import com.spy.mall.common.utils.JwtUtils;
import com.spy.mall.gateway.config.JwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * GlobalFilter 是全局鉴权的接口
 *
 * @Author: spy
 * @Date: 2021/5/15 15:52
 */
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 过滤请求的filters
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * 鉴权基本流程：
         * 1、判断请求在不在拦截名单之中，不在就直接放行
         * 2、获取请求中的token信息，
         * 3、判断token是否为空，为空就重定向到登录页面并且拦截
         * 4、解析token信息，如果解析出现异常 重定向到登录页面并且拦截
         * 5、判断token中ip和当前请求中的ip是否一致，不一致重定向到登录页面并拦截
         * 6、把解析后的登录信息传递给后续服务
         */

        //获取请求对象 ServerHttpRequest
        ServerHttpRequest request = exchange.getRequest();
        //获取响应对象 HttpServletRequest
        ServerHttpResponse response = exchange.getResponse();

        //获取请求地址
        String path = request.getURI().getPath();
        //如果咱们的请求地址中有 类似 /**/auth/**的路径就需要鉴权操作
        AntPathMatcher matcher = new AntPathMatcher();
        //path路径根据匹配规则进行匹配，如果路径中间有auth则需要进行鉴权操作
        boolean result = matcher.match("/**/auth/**", path);
        //需要鉴权
        if (result) {
            // 获取请求报文中的token字符串
            String token = request.getHeaders().getFirst("token");
            if (StringUtils.isBlank(token)) {
                // 如果从头信息中没有获取到token，尝试从cookie中获取token信息
                MultiValueMap<String, HttpCookie> cookies = request.getCookies();
                if (CollectionUtils.isEmpty(cookies) && cookies.containsKey(jwtProperties.getCookieName())) {
                    HttpCookie cookie = cookies.getFirst(jwtProperties.getCookieName());
                    token = cookie.getValue();
                }
            }
            //判断token是否为空，为空就重定向到登录页面并拦截
            if (StringUtils.isBlank(token)) {
                // 设置重定向相关信息
                response.setStatusCode(HttpStatus.SEE_OTHER);
                response.getHeaders().set(HttpHeaders.LOCATION, "http://sso.gmall.com/toLogin.html?returnUrl=" + request.getURI());
                // 拦截请求
                return response.setComplete();
            }
            try {
                //4、解析token信息 如果解析出现异常重定向到登录页面并拦截
                Map<String, Object> map = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
                //5、判断token中的ip 和当前请求的ip 是否一致，不一致重定向到登录页面并拦截
                String ip = map.get("ip").toString();
                String currentIp = IpUtils.getIpAddressAtGateway(request);
                if (!StringUtils.equals(ip, currentIp)) {
                    // 设置重定向相关信息
                    response.setStatusCode(HttpStatus.SEE_OTHER);
                    response.getHeaders().set(HttpHeaders.LOCATION, "http://sso.gmall.com/toLogin.html?returnUrl=" + request.getURI());
                    // 拦截请求
                    return response.setComplete();
                }
                // 6.把解析后的登录信息传递给后续服务
                request.mutate().header("userId", map.get("userId").toString()).build();
                exchange.mutate().request(request).build();
                // 7.放行
                return chain.filter(exchange);
            } catch (Exception e) {
                e.printStackTrace();
                // 设置重定向相关信息
                response.setStatusCode(HttpStatus.SEE_OTHER);
                response.getHeaders().set(HttpHeaders.LOCATION, "http://sso.gmall.com/toLogin.html?returnUrl=" + request.getURI());
                // 拦截请求
                return response.setComplete();
            }

        }

        return null;
    }

    /**
     * 网关的先后执行顺序，值越小越优先执行
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
