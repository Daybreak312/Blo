package com.example.blo.domain.account.service

import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.AccountUsecase
import com.example.blo.domain.account.presentation.dto.request.AccountDeleteRequest
import com.example.blo.domain.account.presentation.dto.request.AccountDormantRequest
import com.example.blo.domain.account.service.exception.AccountNoPermissionException
import org.springframework.stereotype.Service

@Service
class AccountInteractor(
    private val currentAccountProvider: CurrentAccountProvideInteractor,
    private val accountRepository: AccountRepository
) : AccountUsecase {

    override fun deleteAccount(request: AccountDeleteRequest) {
        verifyTargetAccountIdEqualsCurrentAccountId(request.accountId)
        accountRepository.deleteByAccountId(request.accountId)
    }

    override fun dormantAccount(request: AccountDormantRequest) {
        verifyTargetAccountIdEqualsCurrentAccountId(request.accountId)
        currentAccountProvider.getCurrentAccount().isDormant = true
    }

    private fun verifyTargetAccountIdEqualsCurrentAccountId(accountId: String) {
        val currentAccount = currentAccountProvider.getCurrentAccount()
        if (currentAccount.accountId != accountId)
            throw AccountNoPermissionException
    }
}