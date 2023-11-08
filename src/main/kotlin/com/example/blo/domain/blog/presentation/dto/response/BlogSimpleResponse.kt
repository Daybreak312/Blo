package com.example.blo.domain.blog.presentation.dto.response

import com.example.blo.domain.account.presentation.dto.response.AccountSimpleResponse
import com.example.blo.domain.blog.entity.Blog

data class BlogSimpleResponse private constructor(
    val id: Long,
    val name: String,
    val introduction: String,
    val opener: AccountSimpleResponse
) {
    companion object {
        fun of(blog: Blog): BlogSimpleResponse =
            BlogSimpleResponse(
                id = blog.id!!,
                name = blog.name,
                introduction = blog.introduction,
                opener = AccountSimpleResponse.of(blog.opener)
            )
    }
}