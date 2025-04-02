package com.back.domain.post.post.controller

import com.back.domain.post.post.dto.PostDto
import com.back.domain.post.post.service.PostDocService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/posts/search")
class ApiV1PostSearchController(
    private val postDocService: PostDocService
) {
    @GetMapping
    fun getItems(
        @RequestParam(defaultValue = "") kw: String,
    ): Page<PostDto> {
        return postDocService
            .findPageByKwOrder(kw)
            .map { PostDto.from(it) }
    }
}