package com.spy.mall.model.product.resp;

import com.spy.mall.model.product.entity.Category3;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/15 14:27
 */
@Data
@ApiModel(value = "返回对象-商品三级分类", description = "返回对象-商品三级分类列表")
public class CategoryThreeResp {

    @ApiModelProperty(value = "商品三级分类列表")
    private List<Category3> result;

}
