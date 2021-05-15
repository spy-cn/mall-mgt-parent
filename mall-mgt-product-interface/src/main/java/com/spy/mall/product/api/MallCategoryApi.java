package com.spy.mall.product.api;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.resp.CategoryOneResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: spy
 * @Date: 2021/5/15 8:37
 */
@Api(tags = {"1.基本信息管理数据"})
public interface MallCategoryApi {

    /**
     * 查询一级分类列表
     *
     * @return
     */
    @ApiOperation(value = "查询商品一级分类")
    public Result<CategoryOneResp> queryCategoryOneLevel();

}
