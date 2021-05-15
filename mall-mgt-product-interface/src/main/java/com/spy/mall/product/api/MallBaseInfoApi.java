package com.spy.mall.product.api;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.request.AttrInfoRequest;
import com.spy.mall.model.product.resp.AttrInfoResp;
import com.spy.mall.model.product.resp.CategoryOneResp;
import com.spy.mall.model.product.resp.CategoryThreeResp;
import com.spy.mall.model.product.resp.CategoryTwoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: spy
 * @Date: 2021/5/15 8:37
 */
@Api(tags = {"1.基本信息管理数据"})
public interface MallBaseInfoApi {

    /**
     * 查询一级分类列表
     *
     * @return
     */
    @ApiOperation(value = "基本信息-查询商品一级分类")
    Result<CategoryOneResp> queryCategoryOneLevel();

    /**
     * 根据一级分类Id 查询二级分类列表
     *
     * @param category1Id 一级分类Id
     * @return
     */
    @ApiOperation(value = "基本信息-查询商品二级分类")
    Result<CategoryTwoResp> queryCategoryTwoLevel(@ApiParam(value = "商品一级分类Id", required = true, example = "1") Long category1Id);

    /**
     * 根据二级分类Id  查询三级分类列表
     *
     * @param category2Id 二级分类Id
     * @return
     */
    @ApiOperation(value = "基本信息-查询商品三级分类")
    Result<CategoryThreeResp> queryCategoryThreeLevel(@ApiParam(value = "商品二级分类Id", required = true, example = "1") Long category2Id);

    /**
     * 根据三级分类Id 查询平台属性信息
     *
     * @param category3Id 三级分类Id
     * @return
     */
    @ApiOperation(value = "基本信息-查询平台属性信息")
    Result<AttrInfoResp> queryAttrInfo(@ApiParam(value = "商品三级分类Id", required = true, example = "1") Long category3Id);

    /**
     * 添加修改平台属性信息
     *
     * @param request 平台属性 请求对象
     * @return
     */
    @ApiOperation(value = "基本信息-添加/修改平台属性信息")
    Result addAttrInfo(@ApiParam(value = "平台属性请求对象", required = true) AttrInfoRequest request);

    /**
     * 删除平台属性Id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "基本信息-删除平台属性信息")
    Result deleteAttrInfo(@ApiParam(value = "平台属性id", required = true, example = "1") Long id);


}
