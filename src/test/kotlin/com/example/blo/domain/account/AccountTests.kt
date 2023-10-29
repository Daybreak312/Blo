package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.port.`in`.AccountUsecase
import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.account.presentation.dto.request.AccountDeleteRequest
import com.example.blo.domain.account.presentation.dto.request.AccountDormantRequest
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class AccountTests @Autowired constructor(
    private val function: AccountTestFunction,
    private val accountService: AccountUsecase,
    private val accountProvideService: CurrentAccountProvideUsecase,
    private val loadAccountService: UserDetailsService
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

    @Transactional
    @Test
    fun userDetailsServiceTest() {
        val testerAccount = function.createAndSaveInDBAndReturnAccount()
        val loadedAccount = loadAccountService.loadUserByUsername(testerAccount.accountId)
        Assertions.assertNotNull(loadedAccount)
    }

    @Transactional
    @Test
    fun userDetailsServiceLoadNotExistsAccount() {
        Assertions.assertThrows(
            AccountNotFoundException::class.java,
            fun() { loadAccountService.loadUserByUsername("@") }
        )
    }

    @Transactional
    @Test
    fun accountDeleteTest() {
        val testerAccount = function.createAndSaveInDBContextAndReturnAccount()
        accountService.deleteAccount(AccountDeleteRequest(testerAccount.accountId))
        Assertions.assertThrows(
            AccountNotFoundException::class.java,
            fun() { loadAccountService.loadUserByUsername(testerAccount.accountId) }
        )
    }

    @Transactional
    @Test
    fun accountDormantTest() {
        val testerAccount = function.createAndSaveInDBContextAndReturnAccount()
        accountService.dormantAccount(AccountDormantRequest(testerAccount.accountId))
        Assertions.assertTrue(testerAccount.isDormant)
    }
}