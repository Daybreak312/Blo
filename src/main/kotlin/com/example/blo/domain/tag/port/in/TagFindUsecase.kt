package com.example.blo.domain.tag.port.`in`

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.tag.entity.Tag

interface TagFindUsecase {
    fun findTagsInBlog(blog: Blog): List<Tag>
}