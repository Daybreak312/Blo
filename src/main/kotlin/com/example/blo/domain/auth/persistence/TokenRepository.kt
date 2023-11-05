package com.example.blo.domain.auth.persistence

import com.example.blo.domain.auth.entity.RefreshTokenForSave
import org.springframework.data.repository.CrudRepository

interface TokenRepository : CrudRepository<RefreshTokenForSave, String> {
    fun findByToken(token: String): RefreshTokenForSave
    fun deleteByToken(token: String)
}