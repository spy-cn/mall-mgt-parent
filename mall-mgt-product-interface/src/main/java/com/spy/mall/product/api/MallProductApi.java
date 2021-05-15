package com.spy.mall.product.api;

import com.spy.mall.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: spy
 * @Date: 2021/5/15 11:22
 */

@Api(tags = "2.商品信息管理数据")
public interface MallProductApi {


    @ApiOperation(value = "测试接口")
    public Result test();


}
