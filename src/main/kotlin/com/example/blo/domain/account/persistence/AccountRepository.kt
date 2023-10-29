package com.example.blo.domain.account.persistence

import com.example.blo.domain.account.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long?> {
    fun findByAccountId(accountId: String): Account?
    fun existsByAccountId(accountId: String): Boolean
    fun deleteByAccountId(accountId: String)
}