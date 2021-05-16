package com.spy.mall.product.svc.controller;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.product.request.SpuRequest;
import com.spy.mall.model.product.resp.SkuInfoResp;
import com.spy.mall.model.product.resp.SpuBaseInfoResp;
import com.spy.mall.model.product.resp.SpuSaleAttrResp;
import com.spy.mall.model.product.resp.TrademarkResp;
import com.spy.mall.product.api.MallProductApi;
import com.spy.mall.product.svc.service.SkuInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: spy
 * @Date: 2021/5/15 11:24
 */
@RestController
@RequestMapping("/product")
public class ProductController implements MallProductApi {

    @Resource
    private SkuInfoService skuInfoService;

    @Override
    @GetMapping("")
    public Result<SpuBaseInfoResp> queryProductSpu(@RequestParam Long category3Id,
                                                   @RequestParam(required = false, defaultValue = "1") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer size) {
        return null;
    }

    @Override
    @PostMapping("/add_spu")
    public Result addProductSpu(@RequestBody SpuRequest request) {
        return null;
    }

    @Override
    @GetMapping("/sale_attr")
    public Result<SpuSaleAttrResp> queryProductSaleAttr() {
        return null;
    }

    @Override
    @GetMapping("/trade")
    public Result<TrademarkResp> queryProductTrademark() {
        return null;
    }


    @Override
    @GetMapping("/query_sku_info/{skuId}")
    public Result<SkuInfoResp> querySkuInfo(@PathVariable("skuId") Long skuId) {
        SkuInfoResp resp = skuInfoService.getSkuInfoById(skuId);
        return Result.ok(resp);
    }

    @Override
    @GetMapping("/test")
    public Result test() {
        return Result.ok("ok");
    }
}
