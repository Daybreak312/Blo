package com.example.blo.global.security.service

import com.example.blo.domain.auth.entity.Token
import com.example.blo.global.security.jwt.port.`in`.TokenProvideUsecase
import com.example.blo.global.security.jwt.env.JwtProperty
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenProvideInteractor(
    private val jwtProperty: JwtProperty
) : TokenProvideUsecase {

    override fun createToken(accountId: String): Token =
        Token(
            accessToken = createAccessTokenWithPrefix(accountId),
            refreshToken = createRefreshTokenWithPrefix()
        )

    private fun createAccessTokenWithPrefix(accountId: String): String =
        jwtProperty.prefix + createTokenWithSubject(accountId)

    private fun createRefreshTokenWithPrefix(): String =
        jwtProperty.prefix + createTokenWithoutSubject()

    private fun createTokenWithSubject(accountId: String): String {
        val currentDate = Date()
        return Jwts.builder()
            .setSubject(accountId)
            .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
            .setIssuedAt(currentDate)
            .setExpiration(Date(currentDate.time + jwtProperty.accessTokenExp))
            .compact()
    }

    private fun createTokenWithoutSubject(): String {
        val currentDate = Date()
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
            .setIssuedAt(currentDate)
            .setExpiration(Date(currentDate.time + jwtProperty.refreshTokenExp))
            .compact()
    }
}