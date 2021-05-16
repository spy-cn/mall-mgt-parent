package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 8:45
 */

@Data
@ApiModel(value = "销售属性值", description = "销售属性值")
public class SpuSaleAttrValue {

    @ApiModelProperty(value = "销售属性值Id")
    private Long id;
    @ApiModelProperty(value = "spuId")
    private Long spuId;
    @ApiModelProperty(value = "销售属性id")
    private Long baseSaleAttrId;
    @ApiModelProperty(value = "销售属性值名称")
    private String saleAttrValueName;
    @ApiModelProperty(value = "销售属性名称（数据库中冗余数据）")
    private String saleAttrName;

}


