package com.example.blo.domain.blog.port.`in`

import com.example.blo.domain.blog.presentation.dto.request.BlogIntroductionUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogNameUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogTagUpdateRequest

interface BlogUpdateUsecase {
    fun updateBlogName(blogName: String, request: BlogNameUpdateRequest)
    fun updateBlogIntroduction(blogName: String, request: BlogIntroductionUpdateRequest)
    fun updateBlogTag(blogName: String, request: BlogTagUpdateRequest)
}