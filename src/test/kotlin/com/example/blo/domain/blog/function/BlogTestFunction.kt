package com.example.blo.domain.blog.function

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogIntroductionUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogNameUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogTagUpdateRequest

interface BlogTestFunction {
    fun initialize()
    fun createAndSaveInDBandReturnBlog(account: Account): Blog
    fun createAndSaveInDBandReturnBlogWithUpdatedName(account: Account): Blog
    fun createBlogCreateRequest(): BlogCreateRequest
    fun createBlogCreateRequestWithNullIntroduction(): BlogCreateRequest
    fun createBlogNameUpdateRequest(): BlogNameUpdateRequest
    fun createBlogIntroductionUpdateRequest(): BlogIntroductionUpdateRequest
    fun createBlogTagUpdateRequest(): BlogTagUpdateRequest
    fun createBlogTagUpdateRequestWithDuplicatedTags(): BlogTagUpdateRequest
    fun findTestBlog(): Blog?
}