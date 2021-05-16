package com.spy.mall.model.product.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 8:41
 */

@Data
public class SpuSaleAttrDto {

    @ApiModelProperty(value = "SpuId")
    private Long spuId;
    @ApiModelProperty(value = "基本销售属性Id")
    private Long baseSaleAttrId;
    @ApiModelProperty(value = "销售属性名称")
    private String saleAttrName;

    private List<SpuSaleAttrValueDto> spuSaleAttrValueList;

}


