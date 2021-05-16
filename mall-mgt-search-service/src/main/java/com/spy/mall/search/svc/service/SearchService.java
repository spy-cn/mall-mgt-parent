package com.spy.mall.search.svc.service;

import com.spy.mall.model.search.param.SearchParam;
import com.spy.mall.model.search.resp.SearchResp;

/**
 * @Author: spy
 * @Date: 2021/5/16 15:07
 */

public interface SearchService {

    /**
     * ES索引库-搜索
     *
     * @param param
     * @return
     */
    SearchResp search(SearchParam param);

    /**
     * 上架
     *
     * @param skuId
     */
    void onSale(Long skuId);
}
