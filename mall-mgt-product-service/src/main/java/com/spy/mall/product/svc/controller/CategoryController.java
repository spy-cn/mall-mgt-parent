package com.spy.mall.product.svc.controller;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.resp.CategoryOneResp;
import com.spy.mall.product.api.MallCategoryApi;
import com.spy.mall.product.svc.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:23
 */
@RestController
@RequestMapping("/category")
public class CategoryController implements MallCategoryApi {

    @Resource
    private CategoryService categoryService;


    @Override
    @GetMapping("/query_category_1")
    public Result<CategoryOneResp> queryCategoryOneLevel() {
        CategoryOneResp resp = categoryService.queryCategoryOneLevel();
        return Result.ok(resp);
    }


}
