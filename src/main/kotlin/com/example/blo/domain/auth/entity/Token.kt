package com.example.blo.domain.auth.entity

data class Token(
    val accessToken: String,
    val refreshToken: String
)
