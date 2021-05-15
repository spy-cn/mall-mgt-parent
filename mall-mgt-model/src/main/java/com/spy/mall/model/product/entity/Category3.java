package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/15 14:29
 */

@Data
@ApiModel(value = "商品三级分类信息", description = "商品三级分类信息")
public class Category3 {

    @ApiModelProperty(value = "商品三级分类Id")
    private Long id;
    @ApiModelProperty(value = "商品三级分类名称")
    private String name;
    @ApiModelProperty(value = "商品二级分类Id")
    private Long category2Id;

}
