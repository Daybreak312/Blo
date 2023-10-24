package com.example.blo.domain.auth.presentation.dto.request

data class LoginRequest(
    val accountId: String,
    val password: String
)