package com.spy.mall.model.search.resp;

import com.spy.mall.model.product.entity.Trademark;
import com.spy.mall.model.search.mapping.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 12:01
 */

@Data
@ApiModel(value = "返回对象-搜索数据", description = "返回对象-搜索数据")
public class SearchResp {

    @ApiModelProperty(value = "品牌列表")
    private List<Trademark> trademarks;

    private List<SearchAttrResp> attrRespList = new ArrayList<>();
    @ApiModelProperty(value = "商品列表")
    private List<Goods> goodsList = new ArrayList<>();

    @ApiModelProperty(value = "总记录数")
    private Long total;
    @ApiModelProperty(value = "每页显示记录数")
    private Integer pageSize;
    @ApiModelProperty(value = "当前页")
    private Integer pageNo;
    @ApiModelProperty(value = "总页数")
    private Long totalPages;

}
