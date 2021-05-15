package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:08
 */
@Data
@ApiModel(value = "商品一级分类", description = "商品一级分类")
public class Category1 {

    @ApiModelProperty(value = "商品一级分类id")
    private Long id;
    @ApiModelProperty(value = "商品一级分类名称")
    private String name;

}


