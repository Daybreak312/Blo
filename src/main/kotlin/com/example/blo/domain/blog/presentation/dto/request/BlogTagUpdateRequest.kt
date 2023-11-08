package com.example.blo.domain.blog.presentation.dto.request

data class BlogTagUpdateRequest(
    val addTags: List<String>,
    val removeTags: List<String>
)