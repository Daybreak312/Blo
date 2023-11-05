package com.example.blo.domain.tag.port.`in`

import com.example.blo.domain.blog.entity.Blog

interface TagConnectUsecase {
    fun connectTagsToBlog(tagNames: List<String>, blog: Blog)
}