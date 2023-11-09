package com.example.blo.domain.tag.service

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogTagJoinerRepository
import com.example.blo.domain.tag.entity.Tag
import com.example.blo.domain.tag.port.`in`.TagExtractUsecase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Component
class TagExtractInteractor(
    private val blogTagJoinerRepository: BlogTagJoinerRepository
) : TagExtractUsecase {

    override fun extractTagsInBlog(blog: Blog): List<Tag> =
        blogTagJoinerRepository.findAllByBlog(blog).map { it.tag }
}