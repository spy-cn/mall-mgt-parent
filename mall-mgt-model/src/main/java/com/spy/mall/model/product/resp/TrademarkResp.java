package com.spy.mall.model.product.resp;

import com.spy.mall.model.product.entity.Trademark;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 7:03
 */

@Data
@ApiModel(value = "返回对象-品牌属性", description = "返回对象-品牌属性")
public class TrademarkResp {


    @ApiModelProperty(value = "品牌属性列表", notes = "品牌属性列表")
    private List<Trademark> result;


}
