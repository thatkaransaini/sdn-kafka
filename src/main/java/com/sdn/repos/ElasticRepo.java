package com.sdn.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.sdn.entity.JsonEntity;

/**
 * Holds the Elasticsearch CRUD operations for {@link Book} entity.
 */
public interface ElasticRepo extends ElasticsearchRepository<JsonEntity, String> {
}