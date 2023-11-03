package com.example.blo.domain.auth.function

import com.example.blo.domain.auth.presentation.dto.request.LoginRequest
import com.example.blo.domain.auth.presentation.dto.request.SignRequest

interface AuthTestFunction {
    fun createSignRequest(): SignRequest
    fun createLoginRequest(): LoginRequest
}