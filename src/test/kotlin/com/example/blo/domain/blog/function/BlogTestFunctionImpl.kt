package com.example.blo.domain.blog.function

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest
import com.example.blo.domain.tag.port.`in`.TagConnectUsecase
import com.example.blo.env.BlogTestEnv
import org.springframework.stereotype.Component

@Component
class BlogTestFunctionImpl(
    private val blogRepository: BlogRepository,
    private val tagConnector: TagConnectUsecase
) : BlogTestFunction {

    override fun initialize() {
        blogRepository.deleteByName(BlogTestEnv.NAME)
    }

    override fun createAndReturnBlog(account: Account): Blog =
        Blog(BlogTestEnv.NAME, BlogTestEnv.INTRODUCTION, account)

    override fun createAndSaveInDBandReturnBlog(account: Account): Blog {
        val blog = createAndReturnBlog(account)
        tagConnector.connectTagsToBlog(BlogTestEnv.TAGS, blog)
        return blogRepository.save(blog)
    }

    override fun createBlogCreateRequest(): BlogCreateRequest =
        BlogCreateRequest(BlogTestEnv.NAME, BlogTestEnv.INTRODUCTION, BlogTestEnv.TAGS)

    override fun createBlogCreateRequestWithNullIntroduction(): BlogCreateRequest =
        BlogCreateRequest(BlogTestEnv.NAME, null, BlogTestEnv.TAGS)

    override fun findTestBlog(): Blog? =
        blogRepository.findByName(BlogTestEnv.NAME)
}