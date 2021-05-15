package com.spy.mall.product.svc.service.impl;

import com.spy.mall.model.product.entity.Category1;
import com.spy.mall.model.product.resp.CategoryOneResp;
import com.spy.mall.product.svc.mapper.CategoryMapper;
import com.spy.mall.product.svc.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:26
 */

@Service
public class CategoryServiceImpl implements CategoryService {


    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询一级商品分类
     *
     * @return
     */
    @Override
    public CategoryOneResp queryCategoryOneLevel() {
        CategoryOneResp resp = new CategoryOneResp();
        List<Category1> category1List = categoryMapper.selectCategory();
        resp.setResult(category1List);
        return resp;
    }
}
