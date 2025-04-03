package com.back.domain.post.post.repository

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery
import co.elastic.clients.elasticsearch._types.query_dsl.Query
import com.back.domain.post.post.entity.PostDoc
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder
import org.springframework.data.elasticsearch.core.ElasticsearchOperations

class PostDocCustomRepositoryImpl(
    private val elasticsearchOperations: ElasticsearchOperations
) : PostDocCustomRepository {

    override fun findPageByTitleOrContent(kw1: String, kw2: String, pageable: PageRequest): Page<PostDoc> {
        val query: NativeQuery = NativeQueryBuilder()
            .withQuery(
                Query.of { q ->
                    q.bool { b ->
                        b.should(
                            listOf(
                                MatchQuery.of { m -> m.field("title").query(kw1) }._toQuery(),
                                MatchQuery.of { m -> m.field("content").query(kw2) }._toQuery(),
                            )
                        )
                    }
                }
            )
            .withPageable(pageable)
            .build()

        val searchHits = elasticsearchOperations.search(query, PostDoc::class.java)

        return PageImpl(
            searchHits.searchHits.map { it.content },
            pageable,
            searchHits.totalHits
        )
    }
}
