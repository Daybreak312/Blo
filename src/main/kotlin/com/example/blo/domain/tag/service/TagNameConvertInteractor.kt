package com.example.blo.domain.tag.service

import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.tag.entity.Tag
import com.example.blo.domain.tag.persistence.TagRepository
import com.example.blo.domain.tag.port.`in`.TagNameConvertUsecase
import org.springframework.stereotype.Service

@Service
class TagNameConvertInteractor(
    private val tagRepository: TagRepository,
    private val currentAccountProvider: CurrentAccountProvideUsecase
) : TagNameConvertUsecase {

    override fun convertTagNameToTag(tagName: String): Tag =
        tagRepository.findByName(tagName) ?: createTag(tagName)

    private fun createTag(tagName: String): Tag =
        tagRepository.save(Tag(tagName, currentAccountProvider.getCurrentAccount()))

    override fun convertTagNamesToTags(tagNames: List<String>): List<Tag> =
        tagNames.map { convertTagNameToTag(it) }
}