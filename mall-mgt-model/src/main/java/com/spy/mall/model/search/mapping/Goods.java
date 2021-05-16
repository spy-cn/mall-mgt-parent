package com.spy.mall.model.search.mapping;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * indexName:索引名   相当于 mysql中的数据库
 * type：类型         相当于mysql中的表
 * shards：分片
 * replicas：副本
 *
 * @Author: spy
 * @Date: 2021/5/16 17:00
 */
@Data
@Document(indexName = "goods", type = "info", shards = 3, replicas = 2)
public class Goods {

    /**
     * 商品列表字段
     */
    @Field(type = FieldType.Long)
    private Long skuId;
    @Field(type = FieldType.Keyword, index = false)
    private String defaultImage;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Keyword, index = false)
    private String subTitle;

    /**
     * 排序筛选所需字段
     */
    /**
     * 销量
     */
    @Field(type = FieldType.Long)
    private Long sales;
    /**
     * 新品所需字段
     */
    @Field(type = FieldType.Date)
    private Date createTime;
    /**
     * 是否有货
     */
    @Field(type = FieldType.Boolean)
    private Boolean store = false;

    /**
     * 过滤字段
     * 品牌相关字段
     */
    @Field(type = FieldType.Long)
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    @Field(type = FieldType.Keyword)
    private String logo;
    /**
     * 分类相关字段
     */
    @Field(type = FieldType.Long)
    private Long category1Id;

    @Field(type = FieldType.Keyword)
    private String category1Name;

    @Field(type = FieldType.Long)
    private Long category2Id;

    @Field(type = FieldType.Keyword)
    private String category2Name;

    @Field(type = FieldType.Long)
    private Long category3Id;

    @Field(type = FieldType.Keyword)
    private String category3Name;

    /**
     * 热度
     */
    @Field(type = FieldType.Long)
    private Long hotScore = 0L;

    /**
     * 规格参数相关字段
     */
    @Field(type = FieldType.Nested)
    private List<SearchAttrValueVo> attrs;
}
