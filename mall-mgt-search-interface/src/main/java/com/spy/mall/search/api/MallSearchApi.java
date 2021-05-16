package com.spy.mall.search.api;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.search.param.SearchParam;
import com.spy.mall.model.search.resp.SearchResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: spy
 * @Date: 2021/5/16 11:11
 */
@Api(tags = {"1.ES搜索相关数据"})
public interface MallSearchApi {

    /**
     * 初始化映射索引库
     *
     * @return
     */
    @ApiOperation(value = "ES搜索数据-初始化Goods映射索引库", notes = "ES搜索数据-初始化Goods映射索引库")
    public Result createEsIndex();

    /**
     * 删除映射索引库
     *
     * @return
     */
    @ApiOperation(value = "ES搜索数据-删除Goods映射索引库", notes = "ES搜索数据-删除Goods映射索引库")
    public Result deleteIndex();

    /**
     * 搜索
     *
     * @param param
     * @return
     */
    @ApiOperation(value = "ES搜索数据-搜索", notes = "ES搜索数据-搜索")
    Result<SearchResp> search(@ApiParam(value = "搜索请求对象", required = true) SearchParam param);

    /**
     * 上架
     *
     * @param skuId
     * @return
     */
    @ApiOperation(value = "上架")
    public Result onSale(@ApiParam(value = "商品库存Id", required = true) Long skuId);
}
