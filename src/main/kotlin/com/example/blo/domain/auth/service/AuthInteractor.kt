package com.example.blo.domain.auth.service

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import com.example.blo.domain.auth.entity.RefreshTokenForSave
import com.example.blo.domain.auth.entity.Token
import com.example.blo.domain.auth.persistence.TokenRepository
import com.example.blo.domain.auth.port.`in`.AuthUsecase
import com.example.blo.domain.auth.presentation.dto.request.LoginRequest
import com.example.blo.domain.auth.presentation.dto.request.SignRequest
import com.example.blo.domain.auth.presentation.dto.response.TokenResponse
import com.example.blo.domain.auth.service.exception.InUseAccountIdException
import com.example.blo.domain.auth.service.exception.PasswordMismatchException
import com.example.blo.domain.auth.service.exception.PasswordNotEqualsReenteredException
import com.example.blo.global.security.auth.Role
import com.example.blo.global.security.jwt.port.`in`.TokenProvideUsecase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AuthInteractor(
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvideUsecase,
    private val tokenRepository: TokenRepository,
    private val accountRepository: AccountRepository
) : AuthUsecase {

    override fun sign(request: SignRequest): TokenResponse {
        verifySignRequest(request)
        createAndSaveAccount(request)
        return createAndSaveTokenAndReturnResponse(request.accountId)
    }

    private fun createAndSaveAccount(request: SignRequest) {
        accountRepository.save(
            Account(
                name = request.name,
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
                role = Role.COMMON
            )
        )
    }

    private fun verifySignRequest(request: SignRequest) {
        verifyNotUsedAccountId(request.accountId)
        verifyPasswordEqualsReentered(request.password, request.reenteredPassword)
    }

    private fun verifyNotUsedAccountId(accountId: String) {
        if (accountRepository.existsByAccountId(accountId))
            throw InUseAccountIdException
    }

    private fun verifyPasswordEqualsReentered(password: String, reenteredPassword: String) {
        if (password != reenteredPassword)
            throw PasswordNotEqualsReenteredException
    }

    override fun login(request: LoginRequest): TokenResponse {
        verifyLoginRequest(request)
        return createAndSaveTokenAndReturnResponse(request.accountId)
    }

    private fun verifyLoginRequest(request: LoginRequest) {
        if (!accountRepository.existsByAccountId(request.accountId))
            throw AccountNotFoundException
        verifyPasswordMatches(request.password, request.accountId)
    }

    private fun verifyPasswordMatches(password: String, accountId: String) {
        val account = accountRepository.findByAccountId(accountId)
        if (!passwordEncoder.matches(password, account!!.password))
            throw PasswordMismatchException
    }

    private fun createAndSaveTokenAndReturnResponse(accountId: String): TokenResponse {
        val token = tokenProvider.createToken(accountId)
        saveTokenWithAccountId(token, accountId)
        return translateTokenToResponse(token)
    }

    private fun saveTokenWithAccountId(token: Token, accountId: String) {
        tokenRepository.save(
            RefreshTokenForSave(accountId, token.refreshToken)
        )
    }

    private fun translateTokenToResponse(token: Token): TokenResponse =
        TokenResponse.of(token)
}