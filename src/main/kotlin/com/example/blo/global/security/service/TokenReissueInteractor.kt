package com.example.blo.global.security.service

import com.example.blo.domain.auth.presentation.dto.response.TokenResponse
import com.example.blo.global.security.jwt.port.`in`.TokenReissueUsecase
import org.springframework.stereotype.Service

@Service
class TokenReissueInteractor : TokenReissueUsecase {

    override fun reissue(refreshToken: String): TokenResponse {
        TODO("Not yet implemented")
    }
}