package com.example.blo.domain.blog.service

import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.port.`in`.BlogFindUsecase
import com.example.blo.domain.blog.presentation.dto.response.BlogDetailResponse
import com.example.blo.domain.blog.presentation.dto.response.BlogSimpleListResponse
import com.example.blo.domain.blog.presentation.dto.response.BlogSimpleResponse
import com.example.blo.domain.blog.service.exception.BlogNotFoundException
import com.example.blo.domain.tag.port.`in`.TagExtractUsecase
import org.springframework.stereotype.Service

@Service
class BlogFindInteractor(
    private val blogRepository: BlogRepository,
    private val tagExtractor: TagExtractUsecase
) : BlogFindUsecase {

    override fun findBlogDetail(blogName: String): BlogDetailResponse =
        BlogDetailResponse.of(
            blogRepository.findByName(blogName) ?: throw BlogNotFoundException,
            tagExtractor
        )

    override fun findBlogList(): BlogSimpleListResponse =
        BlogSimpleListResponse(
            blogRepository.findAll().map {
                BlogSimpleResponse.of(it)
            }
        )
}