package com.spy.mall.model.search.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/16 12:15
 */

@Data
@ApiModel(value = "返回对象-搜索属性结果", description = "返回对象-搜索属性结果")
public class SearchAttrResp {


    @ApiModelProperty(value = "属性id")
    private Long attrId;
    @ApiModelProperty(value = "当前属性值的所有值")
    private List<String> attrValues = new ArrayList<>();
    @ApiModelProperty(value = "属性名称")
    private String attrName;

}
