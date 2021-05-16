package com.spy.mall.model.product.resp;

import com.spy.mall.model.product.entity.SpuInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 6:25
 */

@Data
@ApiModel(value = "返回对象-SPU基本信息")
public class SpuBaseInfoResp extends PageInfoResp {

    @ApiModelProperty(value = "SPU基本信息列表")
    private List<SpuInfo> result;


}
