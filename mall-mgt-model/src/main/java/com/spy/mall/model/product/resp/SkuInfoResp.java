package com.spy.mall.model.product.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: spy
 * @Date: 2021/5/16 23:33
 */

@Data
@ApiModel(value = "返回对象-SkuInfo信息", description = "返回对象-SkuInfo信息")
public class SkuInfoResp {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long spuId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "sku名称")
    private String skuName;

    @ApiModelProperty(value = "商品规格描述")
    private String skuDesc;

    @ApiModelProperty(value = "重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "品牌(冗余)")
    private Long tmId;

    @ApiModelProperty(value = "三级分类id（冗余)")
    private Long category3Id;

    @ApiModelProperty(value = "默认显示图片(冗余)")
    private String skuDefaultImg;

    @ApiModelProperty(value = "是否销售（1：是 0：否）")
    private Integer isSale;
}
