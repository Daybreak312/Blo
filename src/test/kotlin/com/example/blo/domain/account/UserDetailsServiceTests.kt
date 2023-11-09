package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class UserDetailsServiceTests @Autowired constructor(
    private val function: AccountTestFunction,
    private val userDetailsService: UserDetailsService
) {

    @AfterEach
    @BeforeEach
    fun initialize() {
        function.initialize()
    }

    @Test
    fun userDetailsServiceTest() {
        val testerAccount = function.createAndSaveInDBAndReturnAccount()
        val loadedAccount = userDetailsService.loadUserByUsername(testerAccount.accountId)
        Assertions.assertNotNull(loadedAccount)
    }

    @Test
    fun userDetailsServiceLoadNotExistsAccount() {
        Assertions.assertThrows(
            AccountNotFoundException.javaClass,
            fun() { userDetailsService.loadUserByUsername("@") }
        )
    }
}