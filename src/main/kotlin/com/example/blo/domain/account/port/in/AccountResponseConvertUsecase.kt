package com.example.blo.domain.account.port.`in`

import com.example.blo.domain.account.presentation.dto.response.AccountDetailResponse
import com.example.blo.domain.account.presentation.dto.response.AccountSimpleResponse

interface AccountResponseConvertUsecase {
    fun convertAccountSimpleResponse(accountId: String): AccountSimpleResponse
    fun convertAccountDetailResponse(accountId: String): AccountDetailResponse
}