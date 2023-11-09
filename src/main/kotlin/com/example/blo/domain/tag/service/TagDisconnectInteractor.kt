package com.example.blo.domain.tag.service

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogTagJoinerRepository
import com.example.blo.domain.tag.port.`in`.TagDisconnectUsecase
import com.example.blo.domain.tag.port.`in`.TagNameConvertUsecase
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class TagDisconnectInteractor(
    private val tagNameConverter: TagNameConvertUsecase,
    private val blogTagJoinerRepository: BlogTagJoinerRepository
) : TagDisconnectUsecase {

    override fun disconnectTagsToBlog(tagNames: List<String>, blog: Blog) {
        val tags = tagNameConverter.convertTagNamesToTags(tagNames.toSet().toList())
        tags.map {
            blogTagJoinerRepository.deleteByTag(it)
        }
    }
}