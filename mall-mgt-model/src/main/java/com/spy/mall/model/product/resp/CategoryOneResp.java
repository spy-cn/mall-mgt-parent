package com.spy.mall.model.product.resp;

import com.spy.mall.model.product.entity.Category1;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:14
 */

@Data
@ApiModel(value = "返回对象-商品一级分类", description = "返回对象-商品一级分类")
public class CategoryOneResp {

    @ApiModelProperty(value = "商品一级分类列表")
    private List<Category1> result;

}
