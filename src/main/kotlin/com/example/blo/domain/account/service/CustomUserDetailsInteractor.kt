package com.example.blo.domain.account.service

import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.CustomUserDetailsUsecase
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsInteractor(val accountRepository: AccountRepository) : CustomUserDetailsUsecase {
    override fun loadUserByUsername(username: String?): UserDetails? =
        username?.let { accountRepository.findByAccountId(it) as UserDetails }
}