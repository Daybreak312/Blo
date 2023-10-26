package com.example.blo.global.security.jwt.port.`in`

import com.example.blo.domain.auth.presentation.dto.request.ReissueRequest
import com.example.blo.domain.auth.presentation.dto.response.TokenResponse

interface TokenReissueUsecase {
    fun reissue(request: ReissueRequest): TokenResponse
}