package com.spy.mall.product.svc.service.impl;

import com.spy.mall.model.product.resp.SkuInfoResp;
import com.spy.mall.product.svc.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: spy
 * @Date: 2021/5/16 23:36
 */
@Service
@Slf4j
public class SkuInfoServiceImpl implements SkuInfoService {

    /**
     * 根据skuId获取SkuInfo 信息
     *
     * @param skuId
     * @return
     */
    @Override
    public SkuInfoResp getSkuInfoById(Long skuId) {
        return null;
    }


}


