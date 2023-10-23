package com.example.blo.domain.account.service

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.global.security.auth.exception.SecurityContextEmptyException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CurrentAccountProvideInteractor(
    private val accountRepository: AccountRepository
) : CurrentAccountProvideUsecase {

    override fun getCurrentAccount(): Account =
        accountRepository.findByAccountId(
            SecurityContextHolder.getContext().authentication.name
        ) ?: throw SecurityContextEmptyException
}