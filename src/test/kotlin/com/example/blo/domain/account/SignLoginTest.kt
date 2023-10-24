package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.auth.port.`in`.AuthUsecase
import com.example.blo.domain.auth.presentation.dto.request.LoginRequest
import com.example.blo.domain.auth.presentation.dto.request.SignRequest
import com.example.blo.env.TesterAccountEnv
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class SignLoginTest @Autowired constructor(
    private val function: AccountTestFunction,
    private val authService: AuthUsecase,
    private val accountRepository: AccountRepository
) {

    @Transactional
    @AfterEach
    @BeforeEach
    fun initialize() {
        function.initialize()
    }

    @Test
    fun signTest() {
        val signRequest = createSignRequest()
        authService.sign(signRequest)

        Assertions.assertTrue(
            accountRepository.existsByAccountId(signRequest.accountId)
        )
    }

    fun createSignRequest() =
        SignRequest(
            name = TesterAccountEnv.NAME,
            accountId = TesterAccountEnv.ACCOUNT_ID,
            password = TesterAccountEnv.PASSWORD,
            reenteredPassword = TesterAccountEnv.PASSWORD
        )

    @Test
    fun loginTest() {
        function.createAndSaveInDBAndReturnAccount()

        val loginRequest = createLoginRequest()
        authService.login(loginRequest)
    }

    fun createLoginRequest() =
        LoginRequest(
            accountId = TesterAccountEnv.ACCOUNT_ID,
            password = TesterAccountEnv.PASSWORD
        )
}