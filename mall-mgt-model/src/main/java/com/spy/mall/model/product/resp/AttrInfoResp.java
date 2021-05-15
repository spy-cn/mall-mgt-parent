package com.spy.mall.model.product.resp;

import com.spy.mall.model.product.entity.AttrInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/15 15:02
 */

@Data
@ApiModel(value = "返回对象-平台属性信息", description = "返回对象-平台属性信息")
public class AttrInfoResp {

    @ApiModelProperty(value = "平台属性列表")
    private List<AttrInfo> result;

}
