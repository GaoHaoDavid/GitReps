package com.xt.es_service;

import com.xt.es_service.dao.ProductDao;
import com.xt.es_service.entity.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootTest
class EsServiceApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private ProductDao productDao;

    @Test
    void createIndex() {
        System.out.println("创建索引");
    }


    @Test
    void deleteIndex() {
        boolean flag = template.deleteIndex(Product.class);
        System.out.println("删除索引:"+flag);
    }

    @Test
    void save() {
        Product product=new Product(2L,"华为手机","手机",2999.0,"http://www.baidi.com");
        productDao.save(product);
    }

    @Test
    void termQuery() {
        int pageNum=0;
        int pageSize=2;
        PageRequest pageRequest=PageRequest.of(pageNum,pageSize);
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("title", "小米");
        Iterable<Product> search = productDao.search(queryBuilder,pageRequest);
        for (Product product : search) {
            System.out.println(product);
        }
    }
}
