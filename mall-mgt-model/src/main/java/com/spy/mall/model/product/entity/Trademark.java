package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 7:04
 */

@Data
@ApiModel(value = "品牌属性", description = "品牌属性")
public class Trademark {

    @ApiModelProperty(value = "品牌属性Id", notes = "品牌属性Id")
    private Long id;
    @ApiModelProperty(value = "品牌名称", notes = "品牌名称")
    private String tmName;
    @ApiModelProperty(value = "品牌logo", notes = "品牌logo")
    private String logoUrl;

}
