package com.example.blo.domain.account.service

import com.example.blo.domain.account.persistence.AccountRepository
import com.example.blo.domain.account.port.`in`.AccountResponseConvertUsecase
import com.example.blo.domain.account.presentation.dto.response.AccountDetailResponse
import com.example.blo.domain.account.presentation.dto.response.AccountSimpleResponse
import com.example.blo.domain.account.service.exception.AccountNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountResponseConvertInteractor(
    private val accountRepository: AccountRepository
) : AccountResponseConvertUsecase {

    override fun convertAccountSimpleResponse(accountId: String): AccountSimpleResponse =
        AccountSimpleResponse.of(
            accountRepository.findByAccountId(accountId) ?: throw AccountNotFoundException
        )

    override fun convertAccountDetailResponse(accountId: String): AccountDetailResponse =
        AccountDetailResponse.of(
            accountRepository.findByAccountId(accountId) ?: throw AccountNotFoundException
        )

}