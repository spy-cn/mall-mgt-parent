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
     * ??????
     *
     * @param skuId
     */
    @Override
    public void onSale(Long skuId) {

    }

    /**
     * ???ES???????????????
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
        //1??????????????????????????????
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        searchSourceBuilder.query(boolQueryBuilder);
        //??????????????????
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", keyword).operator(Operator.AND));
        //??????????????????
        //????????????
        List<Long> brandId = param.getBrandId();
        if (!CollectionUtils.isEmpty(brandId)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("brandId", brandId));
        }
        //????????????
        List<Long> categoryId = param.getCategoryId();
        if (!CollectionUtils.isEmpty(categoryId)) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("categoryId", categoryId));
        }
        //??????????????????
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
        //??????????????????
        Boolean store = param.getStore();
        if (store != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("store", store));
        }
        //??????????????????
        List<String> props = param.getProps();
        if (!CollectionUtils.isEmpty(props)) {
            props.forEach(prop -> {
                String[] attr = StringUtils.split(prop, ":");
                if (attr != null && attr.length == 2) {
                    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                    // ????????????id?????????????????????
                    boolQuery.must(QueryBuilders.termQuery("searchAttrs.attrId", attr[0]));
                    // ????????????????????????????????????
                    String[] attrValues = StringUtils.split(attr[1], "-");
                    boolQuery.must(QueryBuilders.termsQuery("searchAttrs.attrValue", attrValues));
                    boolQueryBuilder.filter(QueryBuilders.nestedQuery("searchAttrs", boolQuery, ScoreMode.None));
                }
            });
        }
        //2?????????
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

        //3?????????
        Integer pageNo = param.getPageNo();
        Integer pageSize = param.getPageSize();
        searchSourceBuilder.from((pageNo - 1) * pageSize);
        searchSourceBuilder.size(pageSize);

        //4?????????
        searchSourceBuilder.highlighter(
                new HighlightBuilder()
                        .field("title")
                        .preTags("<font style='color:red'>")
                        .postTags("</font>")
        );

        //5?????????
        //????????????
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("brandIdAgg").field("brandId")
                        .subAggregation(AggregationBuilders.terms("brandNameAgg").field("brandName"))
                        .subAggregation(AggregationBuilders.terms("logoAgg").field("logo"))
        );
        //????????????
        searchSourceBuilder.aggregation(
                AggregationBuilders.terms("categoryIdAgg").field("categoryId")
                        .subAggregation(AggregationBuilders.terms("categoryNameAgg").field("categoryName"))
        );
        //???????????????????????????
        searchSourceBuilder.aggregation(
                AggregationBuilders.nested("attrAgg", "searchAttrs")
                        .subAggregation(AggregationBuilders.terms("attrIdAgg").field("searchAttrs.attrId")
                                .subAggregation(AggregationBuilders.terms("attrNameAgg").field("searchAttrs.attrName"))
                                .subAggregation(AggregationBuilders.terms("attrValueAgg").field("searchAttrs.attrValue")))
        );
        //??????????????????
        searchSourceBuilder.fetchSource(new String[]{"skuId", "defaultImage", "title", "subTitle", "price"}, null);
        System.err.println(searchSourceBuilder);
        return searchSourceBuilder;
    }

    private SearchResp parseResult(SearchResponse response) {
        SearchResp resp = new SearchResp();
        //??????hits
        SearchHits hits = response.getHits();
        //??????????????????
        resp.setTotal(hits.totalHits);
        //????????????????????????
        SearchHit[] hitsHits = hits.getHits();
        List<Goods> goodsList = Stream.of(hitsHits).map(hitsHit -> {
            String json = hitsHit.getSourceAsString();
            Goods goods = JSON.parseObject(json, Goods.class);
            // ????????????????????????????????????title
            Map<String, HighlightField> highlightFields = hitsHit.getHighlightFields();
            HighlightField highlightField = highlightFields.get("title");
            Text[] fragments = highlightField.getFragments();
            goods.setTitle(fragments[0].string());
            return goods;
        }).collect(Collectors.toList());
        resp.setGoodsList(goodsList);
        //?????????????????????????????????????????????map???????????????
        Map<String, Aggregation> aggregationMap = response.getAggregations().asMap();

        //????????????id?????????
        ParsedLongTerms brandIdAgg = (ParsedLongTerms) aggregationMap.get("brandIdAgg");
        List<? extends Terms.Bucket> brandIdAggBuckets = brandIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(brandIdAggBuckets)) {
            resp.setTrademarks(brandIdAggBuckets.stream().map(bucket -> {
                Trademark trademark = new Trademark();
                trademark.setId((bucket).getKeyAsNumber().longValue());
                //????????????Id?????????
                Map<String, Aggregation> subAggregationMap = bucket.getAggregations().asMap();
                // ?????????????????????????????????????????????
                ParsedStringTerms brandNameAgg = (ParsedStringTerms) subAggregationMap.get("brandNameAgg");
                List<? extends Terms.Bucket> nameAggBuckets = brandNameAgg.getBuckets();
                if (!CollectionUtils.isEmpty(nameAggBuckets)) {
                    trademark.setTmName(nameAggBuckets.get(0).getKeyAsString());
                }
                //????????????logo?????????????????????logo
                ParsedStringTerms logoAgg = (ParsedStringTerms) subAggregationMap.get("logoAgg");
                List<? extends Terms.Bucket> logoBuckets = logoAgg.getBuckets();
                if (!CollectionUtils.isEmpty(logoBuckets)) {
                    trademark.setLogoUrl(logoBuckets.get(0).getKeyAsString());
                }
                return trademark;
            }).collect(Collectors.toList()));
        }

        //??????????????????
        ParsedLongTerms categoryIdAgg = (ParsedLongTerms) aggregationMap.get("categoryIdAgg");
        List<? extends Terms.Bucket> categoryIdAggBuckets = categoryIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(categoryIdAggBuckets)) {

        }
        // ????????????????????????????????????????????????????????????
        ParsedNested attrAgg = (ParsedNested) aggregationMap.get("attrAgg");
        // ??????????????????????????????????????????attrId????????????
        ParsedLongTerms attrIdAgg = (ParsedLongTerms) attrAgg.getAggregations().get("attrIdAgg");
        List<? extends Terms.Bucket> attrIdAggBuckets = attrIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(attrIdAggBuckets)) {


        }
        return resp;
    }
}
