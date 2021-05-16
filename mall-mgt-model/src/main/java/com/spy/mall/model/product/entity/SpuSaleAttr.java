package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 6:56
 */

@Data
@ApiModel(value = "销售属性", description = "销售属性")
public class SpuSaleAttr {

    @ApiModelProperty(value = "销售属性ID")
    private Long id;
    @ApiModelProperty(value = "SpuId")
    private Long spuId;
    @ApiModelProperty(value = "基本销售属性Id")
    private Long baseSaleAttrId;
    @ApiModelProperty(value = "销售属性名称")
    private String saleAttrName;


}
