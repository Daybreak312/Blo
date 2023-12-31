package com.example.blo.global.security.service

import com.example.blo.global.security.jwt.env.JwtProperty
import com.example.blo.global.security.jwt.port.`in`.TokenResolveUsecase
import com.example.blo.global.security.service.exception.ExpiredTokenException
import com.example.blo.global.security.service.exception.TokenWithoutPrefixException
import com.example.blo.global.security.service.exception.NotJwtTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenResolveInteractor(
    private val jwtProperty: JwtProperty
) : TokenResolveUsecase {

    override fun resolveAccessTokenToAccountId(accessToken: String): String {
        val accessTokenWithoutPrefix = removePrefix(accessToken)
        return getAccountId(accessTokenWithoutPrefix)
    }

    override fun removePrefix(tokenWithPrefix: String): String {
        verifyIsBearerToken(tokenWithPrefix)
        return tokenWithPrefix.substring(jwtProperty.prefix.length)
    }

    private fun verifyIsBearerToken(token: String) {
        if (!token.startsWith(jwtProperty.prefix))
            throw TokenWithoutPrefixException
    }

    private fun getAccountId(token: String): String {
        val body = getBodyInJwt(token)
        verifyNotExpiredToken(body)
        return body.subject
    }

    private fun getBodyInJwt(jwtToken: String): Claims {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.secretKey).parseClaimsJws(jwtToken).body
        } catch (e: JwtException) {
            throw NotJwtTokenException
        }
    }

    private fun verifyNotExpiredToken(body: Claims) {
        if (body.expiration.before(Date()))
            throw ExpiredTokenException
    }
}