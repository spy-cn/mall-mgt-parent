package com.spy.mall.product.svc.service;

import com.spy.mall.model.product.resp.CategoryOneResp;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:26
 */

public interface CategoryService {

    /**
     * 查询一级商品分类
     *
     * @return
     */
    CategoryOneResp queryCategoryOneLevel();
}
