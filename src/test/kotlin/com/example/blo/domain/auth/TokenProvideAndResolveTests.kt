package com.example.blo.domain.auth

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.global.security.jwt.env.JwtProperty
import com.example.blo.global.security.jwt.port.`in`.TokenProvideUsecase
import com.example.blo.global.security.jwt.port.`in`.TokenResolveUsecase
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@SpringBootTest
class TokenProvideAndResolveTests @Autowired constructor(
    private val jwtProperty: JwtProperty,
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

    @Order(0)
    @Test
    fun tokenProvideAndResolveTest() {
        val account = function.createAndSaveInDBContextAndReturnAccount()
        val token = tokenProvider.createToken(account.accountId)
        val accountIdInToken = tokenResolver.resolveAccessTokenToAccountId(jwtProperty.prefix + token.accessToken)

        Assertions.assertEquals(account.accountId, accountIdInToken)
    }
}