package com.example.blo.domain.blog.port.`in`

import com.example.blo.domain.blog.presentation.dto.response.BlogDetailResponse
import com.example.blo.domain.blog.presentation.dto.response.BlogSimpleListResponse

interface BlogFindUsecase {
    fun findBlogDetail(blogName: String): BlogDetailResponse
    fun findBlogList(): BlogSimpleListResponse
}