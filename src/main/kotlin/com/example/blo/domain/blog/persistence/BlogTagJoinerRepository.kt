package com.example.blo.domain.blog.persistence

import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.entity.BlogTagJoiner
import com.example.blo.domain.tag.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface BlogTagJoinerRepository : JpaRepository<BlogTagJoiner, Long?> {
    fun findAllByBlog(blog: Blog): List<BlogTagJoiner>
    fun deleteByTag(tag: Tag)
}