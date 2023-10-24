package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class AccountProviderTests @Autowired constructor(
    private val function: AccountTestFunction,
    private val accountProvideService: CurrentAccountProvideUsecase
) {

    @Transactional
    @AfterEach
    @BeforeEach
    fun initialize() {
        function.initialize()
    }

    @Transactional
    @Test
    fun accountProvideTest() {
        val testerAccount = function.createAndSaveInDBContextAndReturnAccount()
        val gotCurrentAccount = accountProvideService.getCurrentAccount()
        Assertions.assertEquals(testerAccount.accountId, gotCurrentAccount.accountId)
    }
}