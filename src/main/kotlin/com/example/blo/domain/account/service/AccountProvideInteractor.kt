package com.example.blo.domain.account.service

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.AccountProvideUsecase
import com.example.blo.global.security.auth.exception.SecurityContextEmptyException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class AccountProvideInteractor(
    private val accountRepository: AccountRepository
) : AccountProvideUsecase {
    
    override fun getCurrentAccount(): Account =
        accountRepository.findByAccountId(
            SecurityContextHolder.getContext().authentication.name
        ) ?: throw SecurityContextEmptyException
}