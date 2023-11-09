package com.example.blo.domain.account.functionClass

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.env.AccountTestEnv
import com.example.blo.global.security.auth.Role
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AccountTestFunctionImpl(
    private val passwordEncoder: PasswordEncoder,
    private val accountRepository: AccountRepository
) : AccountTestFunction {

    @Transactional
    override fun initialize() {
        deleteTestAccountInRepository()
        initializeSecurityContextAuthentication()
    }

    private fun deleteTestAccountInRepository() {
        accountRepository.deleteByAccountId(AccountTestEnv.ACCOUNT_ID)
    }

    private fun initializeSecurityContextAuthentication() {
        SecurityContextHolder.getContext().authentication = null
    }

    override fun createAndReturnAccount(): Account =
        Account(
            name = AccountTestEnv.NAME,
            accountId = AccountTestEnv.ACCOUNT_ID,
            password = passwordEncoder.encode(AccountTestEnv.PASSWORD),
            role = Role.COMMON
        )

    override fun createAndSaveInDBAndReturnAccount(): Account {
        val account = createAndReturnAccount()
        saveAccountInRepository(account)
        return account
    }

    override fun createAndSaveInDBContextAndReturnAccount(): Account {
        val account = createAndReturnAccount()
        saveAccountInRepository(account)
        saveAccountInSecurityContextAuthentication(account)
        return account
    }

    override fun createAndSaveInDBContextAndReturnAnotherAccount(): Account {
        val account = createAndReturnAccount()
        account.updateAccountId(AccountTestEnv.ACCOUNT_ID_2)
        saveAccountInRepository(account)
        saveAccountInSecurityContextAuthentication(account)
        return account
    }

    private fun saveAccountInRepository(account: Account) {
        accountRepository.save(account)
    }

    private fun saveAccountInSecurityContextAuthentication(account: Account) {
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(
                account.accountId, "", mutableListOf(GrantedAuthority { account.role.name })
            )
    }
}