package com.spy.mall.product.svc.controller;

import com.spy.mall.common.result.Result;
import com.spy.mall.product.api.MallProductApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: spy
 * @Date: 2021/5/15 11:24
 */
@RestController
@RequestMapping("/product")
public class ProductController implements MallProductApi {

    @Override
    @GetMapping("/test")
    public Result test() {
        return Result.ok("ok");
    }
}
