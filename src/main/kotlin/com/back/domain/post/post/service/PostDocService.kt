package com.back.domain.post.post.service

import com.back.domain.post.post.entity.PostDoc
import com.back.domain.post.post.repository.PostDocRepository
import com.github.f4b6a3.tsid.TsidCreator
import org.springframework.ai.embedding.EmbeddingModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.NoSuchIndexException
import org.springframework.data.elasticsearch.UncategorizedElasticsearchException
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class PostDocService(
    private val elasticsearchOperations: ElasticsearchOperations,
    private val postDocRepository: PostDocRepository,
    private val embeddingModel: EmbeddingModel
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
                embedding = embeddingModel.embed(content)
            )
        )
    }

    private fun genNextId(): String {
        return TsidCreator.getTsid().toString()
    }

    fun findByOrderByIdDesc(): List<PostDoc> {
        return postDocRepository
            .findByOrderByIdDesc()
    }

    fun findByOrderByIdAsc(): List<PostDoc> {
        return postDocRepository
            .findByOrderByIdAsc()
    }

    fun findPageByKwOrder(kw: String): Page<PostDoc> {
        return postDocRepository
            .findPageByTitleOrContent(
                kw,
                kw,
                PageRequest.of(
                    0, 10
                ),
            )
    }

    fun findPageV2ByKwOrder(kw: String): Page<PostDoc> {
        return postDocRepository
            .findPageV2ByTitleOrContent(
                kw,
                kw,
                PageRequest.of(
                    0, 10
                ),
            )
    }

    fun findPageV3ByKwOrder(kw: String): Page<PostDoc> {
        return postDocRepository
            .findPageV3ByTitleOrContent(
                kw,
                kw,
                PageRequest.of(
                    0, 10
                ),
            )
    }

    fun findPageV4ByKwOrder(kw: String): Page<PostDoc> {
        return postDocRepository
            .findPageV4ByTitleOrContent(
                kw,
                kw,
                PageRequest.of(
                    0, 10
                ),
            )
    }

    fun count(): Long {
        return try {
            return postDocRepository.count()
        } catch (_: NoSuchIndexException) {
            0L
        }
    }
}