package com.example.blo.domain.blog.presentation.dto.response

import com.example.blo.domain.account.presentation.dto.response.AccountSimpleResponse
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.tag.port.`in`.TagExtractUsecase
import java.time.LocalDateTime

data class BlogDetailResponse private constructor(
    val id: Long,
    val name: String,
    val introduction: String,
    val tagNames: List<String>,
    val openedAt: LocalDateTime,
    val opener: AccountSimpleResponse
) {
    companion object {
        fun of(blog: Blog, tagExtractor: TagExtractUsecase): BlogDetailResponse =
            BlogDetailResponse(
                id = blog.id!!,
                name = blog.name,
                introduction = blog.introduction,
                tagNames = tagExtractor.extractTagsInBlog(blog).map { it.name },
                openedAt = blog.createdAt,
                opener = AccountSimpleResponse.of(blog.opener)
            )
    }
}