package com.example.blo.domain.account.service

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(private val accountRepository: AccountRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): Account =
        username?.let { accountRepository.findByAccountId(it) } ?: throw AccountNotFoundException
}