package com.example.blo.domain.blog.service

import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.port.`in`.BlogUpdateUsecase
import com.example.blo.domain.blog.presentation.dto.request.BlogIntroductionUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogNameUpdateRequest
import com.example.blo.domain.blog.presentation.dto.request.BlogTagUpdateRequest
import com.example.blo.domain.blog.service.exception.BlogNotFoundException
import com.example.blo.domain.blog.service.exception.InUseBlogNameException
import com.example.blo.domain.tag.port.`in`.TagConnectUsecase
import com.example.blo.domain.tag.port.`in`.TagDisconnectUsecase
import org.springframework.stereotype.Service

@Service
class BlogUpdateInteractor(
    private val blogRepository: BlogRepository,
    private val tagConnector: TagConnectUsecase,
    private val tagDisconnector: TagDisconnectUsecase
) : BlogUpdateUsecase {

    override fun updateBlogName(blogName: String, request: BlogNameUpdateRequest) {
        verifyNotUsedBlogName(request.name)
        val blog = blogRepository.findByName(blogName) ?: throw BlogNotFoundException
        blog.updateName(request.name)
    }

    override fun updateBlogIntroduction(blogName: String, request: BlogIntroductionUpdateRequest) {
        val blog = blogRepository.findByName(blogName) ?: throw BlogNotFoundException
        blog.updateIntroduction(request.introduction)
    }

    override fun updateBlogTag(blogName: String, request: BlogTagUpdateRequest) {
        val blog = blogRepository.findByName(blogName) ?: throw BlogNotFoundException
        tagConnector.connectTagsToBlog(request.addTags, blog)
        tagDisconnector.disconnectTagsToBlog(request.removeTags, blog)
    }

    private fun verifyNotUsedBlogName(name: String) {
        if (blogRepository.existsByName(name))
            throw InUseBlogNameException
    }
}