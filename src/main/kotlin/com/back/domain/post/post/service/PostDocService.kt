package com.back.domain.post.post.service

import com.back.domain.post.post.document.PostDoc
import com.back.domain.post.post.repository.PostDocRepository
import com.github.f4b6a3.tsid.TsidCreator
import org.springframework.data.elasticsearch.NoSuchIndexException
import org.springframework.data.elasticsearch.UncategorizedElasticsearchException
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class PostDocService(
    private val elasticsearchOperations: ElasticsearchOperations,
    private val postDocRepository: PostDocRepository
) {
    fun deleteIndex() {
        try {
            elasticsearchOperations.indexOps(PostDoc::class.java).delete()
        } catch (_: NoSuchIndexException) {

        }
    }

    fun createIndex() {
        try {
            elasticsearchOperations.indexOps(PostDoc::class.java).create()
            val mapping = elasticsearchOperations.indexOps(PostDoc::class.java).createMapping()
            elasticsearchOperations.indexOps(PostDoc::class.java).putMapping(mapping)
        } catch (_: UncategorizedElasticsearchException) {

        }
    }

    fun add(title: String, content: String): PostDoc {
        val id = genNextId()

        return postDocRepository.save(
            PostDoc(
                id = id,
                createDate = LocalDateTime.now(),
                modifyDate = LocalDateTime.now(),
                title = title,
                content = content,
            )
        )
    }

    private fun genNextId(): String {
        return TsidCreator.getTsid().toString()
    }
}