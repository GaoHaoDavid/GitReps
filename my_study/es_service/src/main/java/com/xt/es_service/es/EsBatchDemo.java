package com.xt.es_service.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsBatchDemo {

    public static void main(String[] args) throws IOException {

        RestHighLevelClient esClient=new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));

        //1.批量插入
        BulkRequest req1=new BulkRequest();

        req1.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON,"name","leesin"));
        req1.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","zed"));
        req1.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","jack"));

        BulkResponse resp1=esClient.bulk(req1, RequestOptions.DEFAULT);
        System.out.println(resp1.getTook());
        System.out.println(resp1.getItems());

        //2.批量删除
//        BulkRequest req2=new BulkRequest();
//
//        req1.add(new DeleteRequest().index("user").id("1001"));
//        req1.add(new DeleteRequest().index("user").id("1002"));
//        req1.add(new DeleteRequest().index("user").id("1003"));
//
//        BulkResponse resp2=esClient.bulk(req2, RequestOptions.DEFAULT);
//        System.out.println(resp2.getTook());
//        System.out.println(resp2.getItems());

        esClient.close();
    }
}
