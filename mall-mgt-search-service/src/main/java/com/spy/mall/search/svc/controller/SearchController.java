package com.spy.mall.search.svc.controller;

import com.spy.mall.common.result.Result;
import com.spy.mall.model.search.mapping.Goods;
import com.spy.mall.model.search.param.SearchParam;
import com.spy.mall.model.search.resp.SearchResp;
import com.spy.mall.search.api.MallSearchApi;
import com.spy.mall.search.svc.service.SearchService;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: spy
 * @Date: 2021/5/16 12:50
 */
@RestController
@RequestMapping("/search")
public class SearchController implements MallSearchApi {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Resource
    private SearchService searchService;

    /**
     * 初始化Goods索引库
     *
     * @return
     */
    @Override
    @GetMapping("/inner/createEsIndex")
    public Result createEsIndex() {
        //创建索引库
        elasticsearchRestTemplate.createIndex(Goods.class);
        //映射表关联关系
        elasticsearchRestTemplate.putMapping(Goods.class);
        return Result.ok();
    }

    /**
     * 删除索引库
     *
     * @return
     */
    @Override
    @DeleteMapping
    public Result deleteIndex() {
        elasticsearchRestTemplate.deleteIndex(Goods.class);
        return Result.ok();
    }

    /**
     * 在ES中搜索数据
     *
     * @param param
     * @return
     */
    @Override
    @PostMapping
    public Result<SearchResp> search(SearchParam param) {
        SearchResp resp = searchService.search(param);
        return Result.ok(resp);
    }

    @Override
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable("skuId") Long skuId) {
        searchService.onSale(skuId);
        return Result.ok();
    }
}
