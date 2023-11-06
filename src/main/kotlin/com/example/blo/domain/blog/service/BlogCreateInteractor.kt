package com.example.blo.domain.blog.service

import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.port.`in`.BlogCreateUsecase
import com.example.blo.domain.blog.presentation.dto.request.BlogCreateRequest
import com.example.blo.domain.blog.service.function.BlogFunction
import com.example.blo.domain.tag.port.`in`.TagConnectUsecase
import org.springframework.stereotype.Service

@Service
class BlogCreateInteractor(
    private val function: BlogFunction,
    private val blogRepository: BlogRepository,
    private val tagConnector: TagConnectUsecase,
    private val currentAccountProvider: CurrentAccountProvideUsecase
) : BlogCreateUsecase {

    override fun createBlog(request: BlogCreateRequest) {
        function.verifyNotUsedBlogName(request.name)
        val blog = createBlogEntity(request)
        tagConnector.connectTagsToBlog(request.tagNames, blog)
        blogRepository.save(blog)
    }

    private fun createBlogEntity(request: BlogCreateRequest): Blog =
        Blog(
            name = request.name,
            introduction = request.introduction,
            opener = currentAccountProvider.getCurrentAccount()
        )
}