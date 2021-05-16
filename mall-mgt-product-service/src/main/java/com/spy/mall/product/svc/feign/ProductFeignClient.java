package com.spy.mall.product.svc.feign;

import com.spy.mall.product.api.MallProductApi;
import com.spy.mall.product.svc.feign.fallback.ProductFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @Author: spy
 * @Date: 2021/5/16 23:20
 */
@Service
@FeignClient(value = "mall-mgt-product-service", fallback = ProductFeignClientFallBack.class)
public interface ProductFeignClient extends MallProductApi {


}
