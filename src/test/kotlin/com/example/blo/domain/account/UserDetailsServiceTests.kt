package com.example.blo.domain.account

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@SpringBootTest
class UserDetailsServiceTests @Autowired constructor(
    private val function: AccountTestFunction,
    private val userDetailsService: UserDetailsService
) {

    @Transactional
    @AfterEach
    @BeforeEach
    fun initialize() {
        function.initialize()
    }

    @Transactional
    @Order(3)
    @Test
    fun userDetailsServiceTest() {
        val testerAccount = function.createAndSaveInDBAndReturnAccount()
        val loadedAccount = userDetailsService.loadUserByUsername(testerAccount.accountId)
        Assertions.assertNotNull(loadedAccount)
    }

    @Transactional
    @Order(4)
    @Test
    fun userDetailsServiceLoadNotExistsAccount() {
        Assertions.assertThrows(
            AccountNotFoundException::class.java,
            fun() { userDetailsService.loadUserByUsername("@") }
        )
    }
}