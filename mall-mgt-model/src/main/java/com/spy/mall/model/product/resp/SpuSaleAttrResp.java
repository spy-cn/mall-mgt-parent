package com.spy.mall.model.product.resp;

import com.spy.mall.model.product.entity.SpuSaleAttr;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 6:55
 */

@Data
@ApiModel(value = "返回对象-销售属性", description = "返回对象-销售属性")
public class SpuSaleAttrResp {

    @ApiModelProperty(value = "销售属性列表")
    private List<SpuSaleAttr> result;

}
