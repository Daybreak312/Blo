package com.example.blo.domain.account

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.AccountProvideUsecase
import com.example.blo.global.security.auth.Role
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class AccountTests @Autowired constructor(
    private val accountProvideService: AccountProvideUsecase,
    private val accountRepository: AccountRepository
) {

    @Transactional
    @AfterEach
    @BeforeEach
    fun initialize() {
        deleteTestAccountInRepository()
        initializeSecurityContextAuthentication()
    }

    @Transactional
    @Test
    fun accountProvideTest() {
        val testerAccount = createAndSaveAccount()
        val gotCurrentAccount = accountProvideService.getCurrentAccount()
        Assertions.assertEquals(testerAccount.accountId, gotCurrentAccount.accountId)
    }

    fun createAndSaveAccount(): Account {
        val account = Account(name = "테스터", accountId = "tester", password = "password", role = Role.COMMON)
        saveAccountInRepository(account)
        saveAccountInSecurityContextAuthentication(account)
        return account
    }

    fun saveAccountInRepository(account: Account) {
        accountRepository.save(account)
    }

    fun saveAccountInSecurityContextAuthentication(account: Account) {
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(
                account.accountId, "", mutableListOf(GrantedAuthority { account.role.string })
            )
    }

    fun deleteTestAccountInRepository() {
        accountRepository.deleteAllByAccountId("tester")
    }

    fun initializeSecurityContextAuthentication() {
        SecurityContextHolder.getContext().authentication = null
    }
}