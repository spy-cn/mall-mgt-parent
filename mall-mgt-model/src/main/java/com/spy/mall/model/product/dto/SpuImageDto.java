package com.spy.mall.model.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 8:19
 */

@Data
@ApiModel
public class SpuImageDto {

    @ApiModelProperty(value = "SPU图片名称", required = true)
    private String imgName;
    @ApiModelProperty(value = "SPU图片地址", required = true)
    private String imgUrl;


}
