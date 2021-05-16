package com.spy.mall.model.product.request;

import com.spy.mall.model.product.dto.SpuImageDto;
import com.spy.mall.model.product.dto.SpuSaleAttrDto;
import com.spy.mall.model.product.entity.SpuImage;
import com.spy.mall.model.product.entity.SpuSaleAttr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 7:12
 */

@Data
@ApiModel(value = "请求对象-SPU信息", description = "请求对象-SPU信息")
public class SpuRequest {

    @ApiModelProperty(value = "SPUId", example = "1")
    private Long id;
    @ApiModelProperty(value = "SPU名称", required = true, example = "Apple iPhoneX")
    private String spuName;
    @ApiModelProperty(value = "SPU 描述", required = true, example = "品牌：Apple 商品名称：iPhoneX .....")
    private String description;
    @ApiModelProperty(value = "SPU 图片列表", required = true, example = "[]")
    private List<SpuImageDto> spuImageList;
    @ApiModelProperty(value = "SPU销售属性", required = true, example = "[]")
    private List<SpuSaleAttrDto> spuSaleAttrs;


}

