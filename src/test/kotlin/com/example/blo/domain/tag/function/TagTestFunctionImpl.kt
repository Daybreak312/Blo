package com.example.blo.domain.tag.function

import com.example.blo.domain.tag.persistence.TagRepository
import com.example.blo.env.TagTestEnv
import org.springframework.stereotype.Component

@Component
class TagTestFunctionImpl(
    private val tagRepository: TagRepository
) : TagTestFunction {

    override fun initialize() {
        TagTestEnv.TAGS.map { tagRepository.deleteByName(it) }
    }
}