package com.spy.mall.model.product.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: spy
 * @Date: 2021/5/16 6:36
 */

@Data
@ApiModel(value = "分页信息", description = "分页信息")
public class PageInfoResp {


    @ApiModelProperty(value = "总记录数")
    private Long total;
    @ApiModelProperty(value = "总页码")
    private Long totalPages;
    @ApiModelProperty(value = "当前页")
    private Long currentPage;
    @ApiModelProperty(value = "每页记录数")
    private Long size;


}
