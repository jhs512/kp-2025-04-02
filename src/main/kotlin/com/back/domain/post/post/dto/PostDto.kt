package com.back.domain.post.post.dto

import com.back.domain.post.post.entity.PostDoc
import java.time.LocalDateTime

data class PostDto(
    val id: String,
    val createDate: LocalDateTime,
    val modifyDate: LocalDateTime,
    val title: String,
    val content: String,
) {
    companion object {
        fun from(it: PostDoc): PostDto {
            return PostDto(
                id = it.id,
                createDate = it.createDate,
                modifyDate = it.modifyDate,
                title = it.title,
                content = it.content,
            )
        }
    }
}