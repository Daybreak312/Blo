package com.example.blo.global.security.jwt.port.`in`

interface TokenResolveUsecase {
    fun resolveTokenToAccountId(accessToken: String): String
}