package com.spy.mall.product.svc.mapper;

import com.spy.mall.model.product.entity.Category1;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: spy
 * @Date: 2021/5/15 9:27
 */

@Mapper
public interface CategoryMapper {

    /**
     * 查询商品一级分类
     *
     * @return
     */
    List<Category1> selectCategory();
}
