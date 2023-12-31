package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.AccountUsecase
import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.account.presentation.dto.request.AccountDeleteRequest
import com.example.blo.domain.account.presentation.dto.request.AccountDormantRequest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class AccountTests @Autowired constructor(
    private val function: AccountTestFunction,
    private val accountService: AccountUsecase,
    private val accountProvideService: CurrentAccountProvideUsecase,
    private val accountRepository: AccountRepository
) {

    @AfterEach
    @BeforeEach
    fun initialize() {
        function.initialize()
    }

    @Test
    fun accountProvideTest() {
        val testerAccount = function.createAndSaveInDBContextAndReturnAccount()
        val gotCurrentAccount = accountProvideService.getCurrentAccount()
        Assertions.assertEquals(testerAccount.accountId, gotCurrentAccount.accountId)
    }

    @Test
    fun accountDeleteTest() {
        val testerAccount = function.createAndSaveInDBContextAndReturnAccount()
        accountService.deleteAccount(AccountDeleteRequest(testerAccount.accountId))
        val foundAccount = accountRepository.findByAccountId(testerAccount.accountId)
        Assertions.assertNull(foundAccount)
    }

    @Test
    fun accountDormantTest() {
        val testerAccount = function.createAndSaveInDBContextAndReturnAccount()
        accountService.dormantAccount(AccountDormantRequest(testerAccount.accountId))
        Assertions.assertTrue(testerAccount.isDormant)
    }
}