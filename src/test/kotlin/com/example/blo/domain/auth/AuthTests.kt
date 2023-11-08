package com.example.blo.domain.auth

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.auth.function.AuthTestFunction
import com.example.blo.domain.auth.port.`in`.AuthUsecase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class AuthTests @Autowired constructor(
    private val accountTestFunction: AccountTestFunction,
    private val authTestFunction: AuthTestFunction,
    private val authService: AuthUsecase,
    private val accountRepository: AccountRepository
) {

    @Transactional
    @AfterEach
    @BeforeEach
    fun initialize() {
        accountTestFunction.initialize()
    }

    @Transactional
    @Test
    fun signTest() {
        val signRequest = authTestFunction.createSignRequest()
        authService.sign(signRequest)

        Assertions.assertTrue(
            accountRepository.existsByAccountId(signRequest.accountId)
        )
    }

    @Transactional
    @Test
    fun loginTest() {
        accountTestFunction.createAndSaveInDBAndReturnAccount()

        val loginRequest = authTestFunction.createLoginRequest()
        authService.login(loginRequest)
    }
}