package com.back.domain.post.post.repository

import com.back.domain.post.post.entity.PostDoc
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PostDocRepository : ElasticsearchRepository<PostDoc, String>, PostDocCustomRepository {
    fun findTopByOrderByIdDesc(): PostDoc?
    fun findByOrderByIdDesc(): List<PostDoc>
    fun findByOrderByIdAsc(): List<PostDoc>
    fun findPageV2ByTitleOrContent(kw1: String, kw2: String, pageable: PageRequest): Page<PostDoc>

    @Query(
        """
        {
            "bool": {
                "should": [
                    {
                        "query_string": {
                            "default_operator": "and",
                            "fields": ["title"],
                            "query": "?0"
                        }
                    },
                    {
                        "query_string": {
                            "default_operator": "and",
                            "fields": ["content"],
                            "query": "?1"
                        }
                    }
                ]
            }
        }
    """
    )
    fun findPageV3ByTitleOrContent(kw1: String, kw2: String, pageable: PageRequest): Page<PostDoc>

    @Query(
        """
        {
            "bool": {
                "should": [
                    { "match": { "title": "?0" }},
                    { "match": { "content": "?1" }}
                ]
            }
        }
    """
    )
    fun findPageV4ByTitleOrContent(kw1: String, kw2: String, pageable: PageRequest): Page<PostDoc>
}