package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.global.security.jwt.port.`in`.TokenProvideUsecase
import com.example.blo.global.security.jwt.port.`in`.TokenResolveUsecase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class TokenProvideAndResolveTest @Autowired constructor(
    private val function: AccountTestFunction,
    private val tokenProvider: TokenProvideUsecase,
    private val tokenResolver: TokenResolveUsecase
) {

    @Transactional
    @AfterEach
    @BeforeEach
    fun initialize() {
        function.initialize()
    }

    @Test
    fun tokenProvideAndResolveTest() {
        val account = function.createAndSaveAndReturnAccount()
        val token = tokenProvider.createToken(account.accountId)
        val accountIdInToken = tokenResolver.resolveTokenToAccountId(token.accessToken)

        Assertions.assertEquals(account.accountId, accountIdInToken)
    }
}