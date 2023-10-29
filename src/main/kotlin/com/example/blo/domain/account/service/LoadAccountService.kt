package com.example.blo.domain.account.service

import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class LoadAccountService(private val accountRepository: AccountRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails =
        username?.let { accountRepository.findByAccountId(it) as UserDetails } ?: throw AccountNotFoundException
}