package com.example.blo.domain.tag.service

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogTagJoinerRepository
import com.example.blo.domain.tag.entity.Tag
import com.example.blo.domain.tag.port.`in`.TagFindUsecase
import org.springframework.stereotype.Service

@Service
class TagFindInteractor(
    private val blogTagJoinerRepository: BlogTagJoinerRepository
) : TagFindUsecase {

    override fun findTagsInBlog(blog: Blog): List<Tag> =
        blogTagJoinerRepository.findAllByBlog(blog).map { it.tag }
}