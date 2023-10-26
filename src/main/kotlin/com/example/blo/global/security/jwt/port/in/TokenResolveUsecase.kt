package com.example.blo.global.security.jwt.port.`in`

interface TokenResolveUsecase {
    fun resolveAccessTokenToAccountId(accessToken: String): String
    fun removePrefix(token: String): String
}