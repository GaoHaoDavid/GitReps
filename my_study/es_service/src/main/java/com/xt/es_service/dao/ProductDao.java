package com.xt.es_service.dao;

import com.xt.es_service.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
