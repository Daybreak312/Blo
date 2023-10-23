package com.example.blo.global.security.service

import com.example.blo.domain.auth.entity.Token
import com.example.blo.global.security.jwt.port.`in`.TokenProvideUsecase
import com.example.blo.global.security.jwt.env.JwtProperty
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenProvideInteractor(
    private val jwtProperty: JwtProperty
) : TokenProvideUsecase {

    override fun createToken(accountId: String): Token =
        Token(
            accessToken = createAccessToken(accountId),
            refreshToken = createRefreshToken()
        )

    private fun createAccessToken(accountId: String): String {
        val currentDate = Date()
        return Jwts.builder()
            .setSubject(accountId)
            .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
            .setIssuedAt(currentDate)
            .setExpiration(Date(currentDate.time + jwtProperty.accessTokenExp))
            .compact()
    }

    private fun createRefreshToken(): String {
        val currentDate = Date()
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
            .setIssuedAt(currentDate)
            .setExpiration(Date(currentDate.time + jwtProperty.refreshTokenExp))
            .compact()
    }
}