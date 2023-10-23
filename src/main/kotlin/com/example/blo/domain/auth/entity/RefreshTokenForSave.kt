package com.example.blo.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(timeToLive = 86400060)
data class RefreshTokenForSave(

    @Id
    val accountId: String,

    @Indexed
    val token: String
)
