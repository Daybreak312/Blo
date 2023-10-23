package com.example.blo.domain.auth.presentation.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)