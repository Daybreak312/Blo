package com.example.blo.domain.blog.presentation.dto.request

data class BlogCreateRequest(
    val name: String,
    val introduction: String?,
    val tagNames: List<String>
)