package com.spy.mall.payment.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Author: spy
 * @Date: 2021/5/15 12:01
 */
@Api(tags = {"1.支付宝数据"})
public interface AliPaymentApi {

    /**
     * 去支付宝支付页面
     *
     * @param orderId
     * @return
     */
    @ApiOperation(value = "去支付宝支付页面")
    public String submit(@ApiParam(value = "订单Id", required = true, example = "1") Long orderId);
}
