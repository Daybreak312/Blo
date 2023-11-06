package com.example.blo.domain.tag.port.`in`

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.tag.entity.Tag

interface TagExtractUsecase {
    fun extractTagsInBlog(blog: Blog): List<Tag>
}