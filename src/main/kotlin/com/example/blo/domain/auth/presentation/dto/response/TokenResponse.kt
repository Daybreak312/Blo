package com.example.blo.domain.auth.presentation.dto.response

import com.example.blo.domain.auth.entity.Token

data class TokenResponse private constructor(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(token: Token): TokenResponse =
            TokenResponse(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken
            )
    }
}