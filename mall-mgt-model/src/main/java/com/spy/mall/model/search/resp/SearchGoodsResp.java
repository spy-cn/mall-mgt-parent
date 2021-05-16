package com.spy.mall.model.search.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 12:29
 */
@Data
@ApiModel(value = "返回结果-搜索商品数据", description = "返回结果-搜索商品数据")
public class SearchGoodsResp {

    @ApiModelProperty(value = "商品id")
    private Long id;
    @ApiModelProperty(value = "商品默认图片")
    private String defaultImg;
    @ApiModelProperty(value = "商品")
    private String title;
    @ApiModelProperty(value = "商品价格")
    private Double price;
    @ApiModelProperty(value = "商品创建时间")
    private Date createTime;
    @ApiModelProperty(value = "商品品牌ID")
    private Long tmId;
    @ApiModelProperty(value = "商品品牌名称")
    private String tmName;
    @ApiModelProperty(value = "商品品牌logo")
    private String tmLogoUrl;
    @ApiModelProperty(value = "商品热度")
    private Long hotScore = 0L;
    @ApiModelProperty(value = "商品属性列表")
    private List<SearchAttrResp> attrs;


}
