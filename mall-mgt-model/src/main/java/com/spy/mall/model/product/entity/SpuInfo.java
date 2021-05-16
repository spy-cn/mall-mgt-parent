package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 6:20
 */

@Data
@ApiModel(value = "SPU基本信息", description = "SPU基本信息")
public class SpuInfo {

    @ApiModelProperty(value = "SPU基本信息Id")
    private Long id;
    @ApiModelProperty(value = "SPU名称")
    private String spuName;
    @ApiModelProperty(value = "SPU描述")
    private String description;
    @ApiModelProperty(value = "SPU商品三级分类Id")
    private Long category3Id;
    @ApiModelProperty(value = "SPU商品品牌ID")
    private Long tmId;


}
