package com.spy.mall.model.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 7:20
 */

@Data
@ApiModel(value = "SPU图片信息", description = "SPU图片信息")
public class SpuImage {

    @ApiModelProperty(value = "SPU图片ID")
    private Long id;
    @ApiModelProperty(value = "SPU ID")
    private Long spuId;
    @ApiModelProperty(value = "SPU图片名称")
    private String imgName;
    @ApiModelProperty(value = "SPU图片地址")
    private String imgUrl;

}

