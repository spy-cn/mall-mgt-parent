package com.spy.mall.model.product.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/15 15:12
 */

@Data
@ApiModel(value = "请求对象-平台属性")
public class AttrInfoRequest {

    @ApiModelProperty(value = "平台属性Id")
    private Long id;
    @ApiModelProperty(value = "平台属性名", required = true, example = "颜色")
    private String attrName;
    @ApiModelProperty(value = "三级分类Id", required = true, example = "1")
    private Long category3Id;


}
