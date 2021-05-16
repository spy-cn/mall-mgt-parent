package com.spy.mall.product.svc.feign.fallback;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.request.SpuRequest;
import com.spy.mall.model.product.resp.SpuBaseInfoResp;
import com.spy.mall.model.product.resp.SpuSaleAttrResp;
import com.spy.mall.model.product.resp.TrademarkResp;
import com.spy.mall.product.svc.feign.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: spy
 * @Date: 2021/5/16 23:27
 */
@Slf4j
@Service
public class ProductFeignClientFallBack implements ProductFeignClient {
    @Override
    public Result<SpuBaseInfoResp> queryProductSpu(Long category3Id, Integer page, Integer size) {
        return null;
    }

    @Override
    public Result addProductSpu(SpuRequest request) {
        return null;
    }

    @Override
    public Result<SpuSaleAttrResp> queryProductSaleAttr() {
        return null;
    }

    @Override
    public Result<TrademarkResp> queryProductTrademark() {
        return null;
    }

    @Override
    public Result test() {
        return null;
    }
}
