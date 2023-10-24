package com.example.blo.domain.auth.port.`in`

import com.example.blo.domain.auth.presentation.dto.request.LoginRequest
import com.example.blo.domain.auth.presentation.dto.request.SignRequest
import com.example.blo.domain.auth.presentation.dto.response.TokenResponse

interface AuthUsecase {
    fun sign(request: SignRequest): TokenResponse
    fun login(request: LoginRequest): TokenResponse
}