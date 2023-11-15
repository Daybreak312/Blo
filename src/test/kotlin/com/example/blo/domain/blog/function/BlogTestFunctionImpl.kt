package com.example.blo.domain.blog.function

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogIntroductionUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogNameUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogTagUpdateRequest
import com.example.blo.domain.tag.port.`in`.TagConnectUsecase
import com.example.blo.env.BlogTestEnv
import com.example.blo.env.TagTestEnv
import org.springframework.stereotype.Component

@Component
class BlogTestFunctionImpl(
    private val blogRepository: BlogRepository,
    private val tagConnector: TagConnectUsecase
) : BlogTestFunction {

    override fun initialize() {
        blogRepository.deleteByName(BlogTestEnv.NAME)
    }

    override fun createAndSaveInDBandReturnBlog(account: Account): Blog {
        val blog = Blog(BlogTestEnv.NAME, BlogTestEnv.INTRODUCTION, account)
        blogRepository.save(blog)
        tagConnector.connectTagsToBlog(TagTestEnv.TAGS, blog)
        return blog
    }

    override fun createAndSaveInDBandReturnBlogWithUpdatedName(account: Account): Blog {
        val blog = Blog(BlogTestEnv.NAME, BlogTestEnv.INTRODUCTION, account)
        blog.updateName(BlogTestEnv.NAME_UPDATE)
        blogRepository.save(blog)
        tagConnector.connectTagsToBlog(TagTestEnv.TAGS, blog)
        return blog
    }

    override fun createBlogCreateRequest(): BlogCreateRequest =
        BlogCreateRequest(BlogTestEnv.NAME, BlogTestEnv.INTRODUCTION, TagTestEnv.TAGS)

    override fun createBlogCreateRequestWithNullIntroduction(): BlogCreateRequest =
        BlogCreateRequest(BlogTestEnv.NAME, null, TagTestEnv.TAGS)

    override fun createBlogNameUpdateRequest(): BlogNameUpdateRequest =
        BlogNameUpdateRequest(BlogTestEnv.NAME_UPDATE)

    override fun createBlogIntroductionUpdateRequest(): BlogIntroductionUpdateRequest =
        BlogIntroductionUpdateRequest(BlogTestEnv.INTRODUCTION_UPDATE)

    override fun createBlogTagUpdateRequest(): BlogTagUpdateRequest =
        BlogTagUpdateRequest(TagTestEnv.TAGS_UPDATE_ADD, TagTestEnv.TAGS_UPDATE_REMOVE)

    override fun createBlogTagUpdateRequestWithDuplicatedTags(): BlogTagUpdateRequest =
        BlogTagUpdateRequest(TagTestEnv.TAGS, listOf())

    override fun findTestBlog(): Blog? =
        blogRepository.findByName(BlogTestEnv.NAME)
}