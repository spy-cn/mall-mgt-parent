package com.spy.mall.product.api;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.request.SpuRequest;
import com.spy.mall.model.product.resp.SpuBaseInfoResp;
import com.spy.mall.model.product.resp.SpuSaleAttrResp;
import com.spy.mall.model.product.resp.TrademarkResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

/**
 * SPU:一个IPhoneX手机 就是一个SPU，指的是商品信息聚合的最小单位。
 * SKU:IphoneX手机的具体表现形态，颜色、屏幕尺寸、CPU型号、内存等；可以精确到库存单元。
 * 销售属性:一个SPU会决定一个商品都有哪些销售属性。比如：IphoneX会有颜色、版本、内存等销售属性，T恤只有颜色、尺寸这个销售属性。
 * 平台属性:某个商品有什么属性，由它的三级分类决定，比如：笔记本包括：运行内存、CPU、显卡、硬盘、屏幕尺寸等。
 *
 * @Author: spy
 * @Date: 2021/5/15 11:22
 */

@Api(tags = "2.商品信息管理数据")
public interface MallProductApi {

    /**
     * 根据三级分类id 查询商品SPU
     *
     * @param page        页码
     * @param size        每页记录数
     * @param category3Id 三级分类Id
     * @return
     */
    @ApiOperation(value = "商品属性-查询SPU")
    public Result<SpuBaseInfoResp> queryProductSpu(@ApiParam(value = "三级分类Id", example = "61") Long category3Id,
                                                   @ApiParam(value = "页码", example = "1") Integer page,
                                                   @ApiParam(value = "每页记录数", example = "10") Integer size
    );

    /**
     * 添加SPU信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "商品属性-添加SPU")
    public Result addProductSpu(SpuRequest request);

    /**
     * 查询销售属性
     *
     * @return
     */
    @ApiOperation(value = "销售属性-查询")
    public Result<SpuSaleAttrResp> queryProductSaleAttr();


    /**
     * 查询品牌属性
     *
     * @return
     */
    @ApiOperation(value = "品牌属性-查询")
    public Result<TrademarkResp> queryProductTrademark();


    @ApiOperation(value = "测试接口")
    public Result test();


}
