package com.spy.mall.model.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 8:52
 */

@Data
@ApiModel
public class SpuSaleAttrValueDto {


    @ApiModelProperty(value = "销售属性id", required = true)
    private Long baseSaleAttrId;
    @ApiModelProperty(value = "销售属性值名称", required = true)
    private String saleAttrValueName;

}
