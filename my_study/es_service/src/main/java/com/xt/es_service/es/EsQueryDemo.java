package com.xt.es_service.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;

public class EsQueryDemo {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient esClient=new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //1.全量查询：matchAllQuery
        SearchRequest req1=new SearchRequest();
        req1.indices("user");
        req1.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse resp1 = esClient.search(req1, RequestOptions.DEFAULT);
        SearchHits hits1 = resp1.getHits();

        System.out.println(hits1.getTotalHits());
        System.out.println(resp1.getTook());

        for (SearchHit hit : hits1) {
            System.out.println(hit.getSourceAsString());
        }

        //2.条件查询：termQuery
        SearchRequest req2=new SearchRequest();
        req2.indices("user");
        req2.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("age","30")));
        SearchResponse resp2 = esClient.search(req2, RequestOptions.DEFAULT);
        SearchHits hits2 = resp2.getHits();

        System.out.println(hits2.getTotalHits());
        System.out.println(resp2.getTook());

        for (SearchHit hit : hits2) {
            System.out.println(hit.getSourceAsString());
        }

        //3.分页查询
        SearchRequest req3=new SearchRequest();
        req3.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.from(0);
        builder.size(2);
        //按某一字段排序
        builder.sort("age", SortOrder.ASC);
        //过滤字段
        String[] includes={"name"};
        String[] excludes={""};
        builder.fetchSource(includes,excludes);

        req3.source(builder);
        SearchResponse resp3 = esClient.search(req3, RequestOptions.DEFAULT);
        SearchHits hits3 = resp3.getHits();

        System.out.println(hits3.getTotalHits());
        System.out.println(resp3.getTook());

        for (SearchHit hit : hits3) {
            System.out.println(hit.getSourceAsString());
        }

        //4.组合查询
        SearchRequest req4=new SearchRequest();
        req4.indices("user");

        SearchSourceBuilder builder1 = new SearchSourceBuilder();

        //设置查询条件：must-等于 mustNot-不等于 should-或者
//        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        queryBuilder.must(QueryBuilders.matchQuery("age",30));
//        queryBuilder.must(QueryBuilders.matchQuery("sex","男"));
//        queryBuilder.mustNot(QueryBuilders.matchQuery("sex","男"));
//        queryBuilder.should(QueryBuilders.matchQuery("age",30));
//        queryBuilder.should(QueryBuilders.matchQuery("age",30));
//        builder1.query(queryBuilder);

        //范围查询：gte-大于 lte-小于
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//        rangeQuery.gte(30);
//        rangeQuery.lte(40);
//        builder1.query(rangeQuery);
//        req4.source(builder1);

        builder1.query(QueryBuilders.fuzzyQuery("name","leesin").fuzziness(Fuzziness.AUTO));

        SearchResponse resp4 = esClient.search(req4, RequestOptions.DEFAULT);
        SearchHits hits4 = resp4.getHits();

        System.out.println(hits4.getTotalHits());
        System.out.println(resp4.getTook());

        for (SearchHit hit : hits4) {
            System.out.println(hit.getSourceAsString());
        }

        //5.聚合查询
        SearchRequest req5=new SearchRequest();
        req5.indices("user");

        SearchSourceBuilder builder2 = new SearchSourceBuilder();

        AggregationBuilder aggBuilder= AggregationBuilders.max("maxAge").field("age");
//        AggregationBuilder aggBuilder= AggregationBuilders.avg("avgAge").field("age");
//        AggregationBuilder aggBuilder= AggregationBuilders.count("countAge").field("age");
        builder2.aggregation(aggBuilder);

        req5.source(builder2);
        SearchResponse resp5 = esClient.search(req5, RequestOptions.DEFAULT);
        SearchHits hits5 = resp5.getHits();

        System.out.println(hits5.getTotalHits());
        System.out.println(resp5.getTook());

        for (SearchHit hit : hits5) {
            System.out.println(hit.getSourceAsString());
        }

        //6.分组查询
        SearchRequest req6=new SearchRequest();
        req6.indices("user");

        SearchSourceBuilder builder3 = new SearchSourceBuilder();

        AggregationBuilder aggBuilder1= AggregationBuilders.terms("ageGroup").field("age");
        builder3.aggregation(aggBuilder1);

        req6.source(builder3);
        SearchResponse resp6 = esClient.search(req6, RequestOptions.DEFAULT);
        SearchHits hits6 = resp6.getHits();

        System.out.println(hits6.getTotalHits());
        System.out.println(resp6.getTook());

        for (SearchHit hit : hits6) {
            System.out.println(hit.getSourceAsString());
        }

        esClient.close();
    }
}
