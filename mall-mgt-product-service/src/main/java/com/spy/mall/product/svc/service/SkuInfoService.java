package com.spy.mall.product.svc.service;

import com.spy.mall.model.product.resp.SkuInfoResp;

/**
 * @Author: spy
 * @Date: 2021/5/16 23:35
 */

public interface SkuInfoService {

    /**
     * 查询库存单元
     *
     * @param skuId
     * @return
     */
    SkuInfoResp getSkuInfoById(Long skuId);
}
