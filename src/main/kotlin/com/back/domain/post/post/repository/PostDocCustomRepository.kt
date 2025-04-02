package com.back.domain.post.post.repository

import com.back.domain.post.post.entity.PostDoc
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface PostDocCustomRepository {
    fun findPageByTitleOrContent(kw1: String, kw2: String, pageable: PageRequest): Page<PostDoc>
}