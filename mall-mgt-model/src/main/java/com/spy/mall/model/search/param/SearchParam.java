package com.spy.mall.model.search.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * ?keyword=手机&brandId=1,2&categoryId=225&props=4:8G-12G&props=5:128G-256G
 * &priceFrom=1000&priceTo=3000&store=true&sort=1&pageNum=2
 *
 * @Author: spy
 * @Date: 2021/5/16 11:13
 */
@Data
@ApiModel(value = "搜索参数对象", description = "搜索参数对象")
public class SearchParam {
    /**
     * 一、二、三级分类Id
     */
    private List<Long> categoryId;

    /**
     * 品牌Id
     */
    private List<Long> brandId;
    /**
     * 要搜索的关键字
     */
    private String keyword;

    /**
     * 排序：0-综合排序 1-价格降序 2-价格升序 3-销量的降序 4-新品降序
     */
    private Integer sort;

    /**
     * 规格参数的过滤条件: ["4:8G-12G", "5:128G-256G"]
     */
    private List<String> props;

    /**
     * 价格区间过滤
     */
    private Double priceFrom;
    private Double priceTo;
    /**
     * 是否有货
     */
    private Boolean store;
    /**
     * 分页参数
     */
    private Integer pageNo = 1;
    private Integer pageSize = 10;
}
