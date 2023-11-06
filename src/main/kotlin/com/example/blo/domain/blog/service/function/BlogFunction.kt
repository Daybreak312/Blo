package com.example.blo.domain.blog.service.function

import com.example.blo.domain.blog.entity.Blog

interface BlogFunction {
    fun verifyMasterOfBlog(blog: Blog)
    fun verifyNotUsedBlogName(name: String)
}