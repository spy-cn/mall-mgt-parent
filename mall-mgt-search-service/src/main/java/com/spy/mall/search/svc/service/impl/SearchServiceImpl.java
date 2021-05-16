package com.spy.mall.search.svc.service.impl;

import com.alibaba.fastjson.JSON;
import com.spy.mall.model.product.entity.Trademark;
import com.spy.mall.model.search.mapping.Goods;
import com.spy.mall.model.search.param.SearchParam;
import com.spy.mall.model.search.resp.SearchResp;
import com.spy.mall.search.svc.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: spy
 * @Date: 2021/5/16 15:09
 */

@Service
public class SearchServiceImpl implements SearchService {

    private static final String GOODS = "goods";

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 上架
     *
     * @param skuId
     */
    @Override
    public void onSale(Long skuId) {

    }

    /**
     * 在ES中搜索数据
     *
     * @param param
     * @return
     */
    @Override
    public SearchResp search(SearchParam param) {
        try {
            SearchRequest searchRequest = new SearchRequest(new String[]{GOODS}, buildDsl(param));
            SearchResponse response = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchResp searchResp = this.parseResult(response);
            return searchResp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SearchSourceBuilder buildDsl(SearchParam param) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String keyword = param.getKeyword();
        if (StringUtils.isBlank(keyword)) {
            return searchSourceBuilder;
        }
        //1、构建查询及过滤条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        searchSourceBuilder.query(boolQueryBuilder);
        //构建匹配查询
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", keyword).operator(Operator.AND));
        //构建过滤条件
        //品牌过滤
        List<Long> brandId = param.getBrandId();
        if (!CollectionUtils.isEmpty(brandId)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandId", brandId));
        }
        //分类过滤
        List<Long> categoryId = param.getCategoryId();
        if (!CollectionUtils.isEmpty(categoryId)) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("categoryId", categoryId));
        }
        //价格区间过滤
        Double priceFrom = param.getPriceFrom();
        Double priceTo = param.getPriceTo();
        if (priceFrom != null || priceTo != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");
            if (priceFrom != null) {
                rangeQueryBuilder.gte(priceFrom);
            }
            if (priceTo != null) {
                rangeQueryBuilder.lte(priceTo);
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }
        //是否有货过滤
        Boolean store = param.getStore();
        if (store != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("store", store));
        }
        //规格参数过滤
        List<String> props = param.getProps();
        if (!CollectionUtils.isEmpty(props)) {
            props.forEach(prop -> {
                String[] attr = StringUtils.split(prop, ":");
                if (attr != null && attr.length == 2) {
                    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                    // 规格参数id单词条查询条件
                    boolQuery.must(QueryBuilders.termQuery("searchAttrs.attrId", attr[0]));
                    // 规格参数值多词条查询条件
                    String[] attrValues = StringUtils.split(attr[1], "-");
                    boolQuery.must(QueryBuilders.termsQuery("searchAttrs.attrValue", attrValues));
                    boolQueryBuilder.filter(QueryBuilders.nestedQuery("searchAttrs", boolQuery, ScoreMode.None));
                }
            });
        }
        //2、排序
        Integer sort = param.getSort();
        if (sort != null) {
            switch (sort) {
                case 1:
                    searchSourceBuilder.sort("price", SortOrder.DESC);
                    break;
                case 2:
                    searchSourceBuilder.sort("price", SortOrder.ASC);
                    break;
                case 3:
                    searchSourceBuilder.sort("sales", SortOrder.DESC);
                    break;
                case 4:
                    searchSourceBuilder.sort("createTime", SortOrder.DESC);
                    break;
                default:
                    break;
            }
        }

        //3、分页
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        searchSourceBuilder.from((pageNo - 1) * pageSize);
        searchSourceBuilder.size(pageSize);

        //4、高亮
        searchSourceBuilder.highlighter(
                new HighlightBuilder()
                        .field("title")
                        .preTags("<font style='color:red'>")
                        .postTags("</font>")
        );

        //5、聚合
        //品牌聚合
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("brandIdAgg").field("brandId")
                        .subAggregation(AggregationBuilders.terms("brandNameAgg").field("brandName"))
                        .subAggregation(AggregationBuilders.terms("logoAgg").field("logo"))
        );
        //分类聚合
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("categoryIdAgg").field("categoryId")
                        .subAggregation(AggregationBuilders.terms("categoryNameAgg").field("categoryName"))
        );
        //规格参数的嵌套聚合
        searchSourceBuilder.aggregation(
                AggregationBuilders.nested("attrAgg", "searchAttrs")
                        .subAggregation(AggregationBuilders.terms("attrIdAgg").field("searchAttrs.attrId")
                                .subAggregation(AggregationBuilders.terms("attrNameAgg").field("searchAttrs.attrName"))
                                .subAggregation(AggregationBuilders.terms("attrValueAgg").field("searchAttrs.attrValue")))
        );
        //搜索结果过滤
        searchSourceBuilder.fetchSource(new String[]{"skuId", "defaultImage", "title", "subTitle", "price"}, null);
        System.err.println(searchSourceBuilder);
        return searchSourceBuilder;
    }

    private SearchResp parseResult(SearchResponse response) {
        SearchResp resp = new SearchResp();
        //解析hits
        SearchHits hits = response.getHits();
        //总命中记录数
        resp.setTotal(hits.totalHits);
        //解析当前页的数据
        SearchHit[] hitsHits = hits.getHits();
        List<Goods> goodsList = Stream.of(hitsHits).map(hitsHit -> {
            String json = hitsHit.getSourceAsString();
            Goods goods = JSON.parseObject(json, Goods.class);
            // 获取高亮结果集替换掉普通title
            Map<String, HighlightField> highlightFields = hitsHit.getHighlightFields();
            HighlightField highlightField = highlightFields.get("title");
            Text[] fragments = highlightField.getFragments();
            goods.setTitle(fragments[0].string());
            return goods;
        }).collect(Collectors.toList());
        resp.setGoodsList(goodsList);
        //解析聚合结果，获取所有聚合，以map的形式接受
        Map<String, Aggregation> aggregationMap = response.getAggregations().asMap();

        //获取品牌id的聚合
        ParsedLongTerms brandIdAgg = (ParsedLongTerms) aggregationMap.get("brandIdAgg");
        List<? extends Terms.Bucket> brandIdAggBuckets = brandIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(brandIdAggBuckets)) {
            resp.setTrademarks(brandIdAggBuckets.stream().map(bucket -> {
                Trademark trademark = new Trademark();
                trademark.setId((bucket).getKeyAsNumber().longValue());
                //获取品牌Id子聚合
                Map<String, Aggregation> subAggregationMap = bucket.getAggregations().asMap();
                // 解析品牌名称子聚合获取品牌名称
                ParsedStringTerms brandNameAgg = (ParsedStringTerms) subAggregationMap.get("brandNameAgg");
                List<? extends Terms.Bucket> nameAggBuckets = brandNameAgg.getBuckets();
                if (!CollectionUtils.isEmpty(nameAggBuckets)) {
                    trademark.setTmName(nameAggBuckets.get(0).getKeyAsString());
                }
                //解析品牌logo子聚合获取品牌logo
                ParsedStringTerms logoAgg = (ParsedStringTerms) subAggregationMap.get("logoAgg");
                List<? extends Terms.Bucket> logoBuckets = logoAgg.getBuckets();
                if (!CollectionUtils.isEmpty(logoBuckets)) {
                    trademark.setLogoUrl(logoBuckets.get(0).getKeyAsString());
                }
                return trademark;
            }).collect(Collectors.toList()));
        }

        //获取分类聚合
        ParsedLongTerms categoryIdAgg = (ParsedLongTerms) aggregationMap.get("categoryIdAgg");
        List<? extends Terms.Bucket> categoryIdAggBuckets = categoryIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(categoryIdAggBuckets)) {

        }
        // 获取规格参数聚合并解析出规格参数过滤列表
        ParsedNested attrAgg = (ParsedNested) aggregationMap.get("attrAgg");
        // 获取嵌套聚合中的子聚合，就是attrId的子聚合
        ParsedLongTerms attrIdAgg = (ParsedLongTerms) attrAgg.getAggregations().get("attrIdAgg");
        List<? extends Terms.Bucket> attrIdAggBuckets = attrIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(attrIdAggBuckets)) {


        }
        return resp;
    }
}
