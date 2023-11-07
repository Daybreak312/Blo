package com.example.blo.domain.tag.port.`in`

import com.example.blo.domain.tag.entity.Tag

interface TagNameConvertUsecase {
    fun convertTagNamesToTags(tagNames: List<String>): List<Tag>
    fun convertTagNameToTag(tagName: String): Tag
}