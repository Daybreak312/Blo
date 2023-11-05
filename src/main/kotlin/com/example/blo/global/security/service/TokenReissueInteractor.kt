package com.example.blo.global.security.service

import com.example.blo.domain.auth.persistence.TokenRepository
import com.example.blo.domain.auth.presentation.dto.request.ReissueRequest
import com.example.blo.domain.auth.presentation.dto.response.TokenResponse
import com.example.blo.global.security.jwt.port.`in`.TokenProvideUsecase
import com.example.blo.global.security.jwt.port.`in`.TokenReissueUsecase
import com.example.blo.global.security.jwt.port.`in`.TokenResolveUsecase
import org.springframework.stereotype.Service

@Service
class TokenReissueInteractor(
    private val tokenRepository: TokenRepository,
    private val tokenProvider: TokenProvideUsecase,
    private val tokenResolver: TokenResolveUsecase
) : TokenReissueUsecase {

    override fun reissue(request: ReissueRequest): TokenResponse {
        val refreshTokenWithoutPrefix = tokenResolver.removePrefix(request.refreshToken)
        val savedRefreshToken = tokenRepository.findByToken(refreshTokenWithoutPrefix)
        tokenRepository.deleteByToken(refreshTokenWithoutPrefix)
        return createTokenResponse(savedRefreshToken.accountId)
    }

    private fun createTokenResponse(accountId: String): TokenResponse {
        val token = tokenProvider.createToken(accountId)
        return TokenResponse.of(token)
    }
}