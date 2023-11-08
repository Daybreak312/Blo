package com.example.blo.domain.blog.function

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest

interface BlogTestFunction {
    fun initialize()
    fun createAndReturnBlog(account: Account): Blog
    fun createAndSaveInDBandReturnBlog(account: Account): Blog
    fun createBlogCreateRequest(): BlogCreateRequest
    fun createBlogCreateRequestWithNullIntroduction(): BlogCreateRequest
    fun findTestBlog(): Blog?
}