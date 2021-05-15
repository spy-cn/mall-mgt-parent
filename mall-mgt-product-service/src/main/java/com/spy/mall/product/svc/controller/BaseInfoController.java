package com.spy.mall.product.svc.controller;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.request.AttrInfoRequest;
import com.spy.mall.model.product.resp.AttrInfoResp;
import com.spy.mall.model.product.resp.CategoryOneResp;
import com.spy.mall.model.product.resp.CategoryThreeResp;
import com.spy.mall.model.product.resp.CategoryTwoResp;
import com.spy.mall.product.api.MallBaseInfoApi;
import com.spy.mall.product.svc.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:23
 */
@RestController
@RequestMapping("/base")
public class BaseInfoController implements MallBaseInfoApi {

    @Resource
    private CategoryService categoryService;


    @Override
    @GetMapping("/query_category_1")
    public Result<CategoryOneResp> queryCategoryOneLevel() {
        CategoryOneResp resp = categoryService.queryCategoryOneLevel();
        return Result.ok(resp);
    }

    @Override
    @GetMapping("/query_category_2/{category1Id}")
    public Result<CategoryTwoResp> queryCategoryTwoLevel(@PathVariable Long category1Id) {
        return null;
    }

    @Override
    @GetMapping("/query_category_3/{category2Id}")
    public Result<CategoryThreeResp> queryCategoryThreeLevel(@PathVariable Long category2Id) {
        return null;
    }

    @Override
    @GetMapping("/attr_info/{category3Id}")
    public Result<AttrInfoResp> queryAttrInfo(@PathVariable Long category3Id) {
        return null;
    }

    @Override
    @PostMapping("/attr_info/add")
    public Result addAttrInfo(@RequestBody AttrInfoRequest request) {
        return null;
    }

    @Override
    @DeleteMapping("/attr_info/{id}")
    public Result deleteAttrInfo(@PathVariable Long id) {
        return null;
    }


}
