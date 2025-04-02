package com.back.domain.post.post.repository

import com.back.domain.post.post.entity.PostDoc
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository


interface PostDocRepository : ElasticsearchRepository<PostDoc, String>, PostDocCustomRepository {
    fun findTopByOrderByIdDesc(): PostDoc?
    fun findByOrderByIdDesc(): List<PostDoc>
    fun findByOrderByIdAsc(): List<PostDoc>
}