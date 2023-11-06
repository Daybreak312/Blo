package com.example.blo.domain.blog.port.`in`

import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest

interface BlogCreateUsecase {
    fun createBlog(request: BlogCreateRequest)
}