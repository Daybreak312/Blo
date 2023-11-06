package com.example.blo.domain.blog.port.`in`

import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogIntroductionUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogNameUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogTagUpdateRequest

interface BlogCreateUsecase {
    fun createBlog(request: BlogCreateRequest)
}