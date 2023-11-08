package com.example.blo.domain.tag.persistence

import com.example.blo.domain.tag.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long?> {
    fun findByName(content: String): Tag?
    fun deleteByName(name: String)
}