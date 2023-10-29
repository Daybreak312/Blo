package com.example.blo.domain.account.port.`in`

import com.example.blo.domain.account.presentation.dto.request.AccountDeleteRequest
import com.example.blo.domain.account.presentation.dto.request.AccountDormantRequest

interface AccountUsecase {
    fun dormantAccount(request: AccountDormantRequest)
    fun deleteAccount(request: AccountDeleteRequest)
}