package com.back.domain.post.post.repository

import com.back.domain.post.post.document.PostDoc
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository


interface PostDocRepository : ElasticsearchRepository<PostDoc, String> {
    fun findTopByOrderByIdDesc(): PostDoc?
}