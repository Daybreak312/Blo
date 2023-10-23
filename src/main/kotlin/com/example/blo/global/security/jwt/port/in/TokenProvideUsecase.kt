package com.example.blo.global.security.jwt.port.`in`

import com.example.blo.domain.auth.entity.Token

interface TokenProvideUsecase {
    fun createToken(accountId: String): Token
}