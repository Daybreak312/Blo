package com.example.blo.domain.blog.persistence

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.blog.entity.Blog
import org.springframework.data.jpa.repository.JpaRepository

interface BlogRepository : JpaRepository<Blog, Long?> {
    fun existsByName(name: String): Boolean
    fun findByName(name: String): Blog?
    fun findAllByOpener(opener: Account): List<Blog>
    fun deleteByName(name: String)
}