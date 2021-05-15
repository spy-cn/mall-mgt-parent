package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/15 14:59
 */

@Data
@ApiModel(value = "商品属性信息", description = "商品属性信息")
public class AttrInfo {

    @ApiModelProperty(value = "商品属性Id")
    private Long id;
    @ApiModelProperty(value = "商品属性名称")
    private String attrName;
    @ApiModelProperty(value = "三级分类Id")
    private Long categoryId;
    @ApiModelProperty(value = "三级分类")
    private Long categoryLevel;

}
