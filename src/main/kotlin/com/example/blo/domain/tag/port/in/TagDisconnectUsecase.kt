package com.example.blo.domain.tag.port.`in`

import com.example.blo.domain.blog.entity.Blog

interface TagDisconnectUsecase {
    fun disconnectTagsToBlog(tagNames: List<String>, blog: Blog)
}