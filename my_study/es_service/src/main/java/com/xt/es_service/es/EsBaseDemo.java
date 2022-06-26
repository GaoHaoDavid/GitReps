package com.xt.es_service.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xt.es_service.entity.User;
import org.apache.http.HttpHost;
import org.apache.tomcat.util.http.RequestUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsBaseDemo {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

         //1.添加索引
        CreateIndexRequest req1 = new CreateIndexRequest("user");
        CreateIndexResponse resp1 = esClient.indices().create(req1, RequestOptions.DEFAULT);

        //2.获取索引
        GetIndexRequest req2 = new GetIndexRequest("user");
        GetIndexResponse resp2 = esClient.indices().get(req2, RequestOptions.DEFAULT);

        System.out.println(resp2.getAliases());
        System.out.println(resp2.getMappings());
        System.out.println(resp2.getSettings());

        //3.添加对象
        IndexRequest req3=new IndexRequest();
        req3.index("user").id("41");
        User user=new User("admin",30,"男");

        ObjectMapper mapper=new ObjectMapper();
        String str = mapper.writeValueAsString(user);
        req3.source(str, XContentType.JSON);
        IndexResponse resp3 = esClient.index(req3, RequestOptions.DEFAULT);
        System.out.println(resp3.getResult());

        //4.修改对象
        UpdateRequest req4=new UpdateRequest();
        req4.index("user").id("41");
        req4.doc(XContentType.JSON,"sex","女","name","sore");
        UpdateResponse resp4= esClient.update(req4, RequestOptions.DEFAULT);
        System.out.println(resp4.getResult());

        //5.查找对象
        GetRequest req5=new GetRequest();
        req5.index("user").id("41");
        GetResponse resp5=esClient.get(req5, RequestOptions.DEFAULT);
        System.out.println(resp5.getSourceAsString());
        esClient.close();
    }

}
