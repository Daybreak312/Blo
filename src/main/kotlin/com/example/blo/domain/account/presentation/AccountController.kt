package com.example.blo.domain.account.presentation

import com.example.blo.domain.account.port.`in`.AccountUsecase
import com.example.blo.domain.account.presentation.dto.request.AccountDeleteRequest
import com.example.blo.domain.account.presentation.dto.request.AccountDormantRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/account")
@RestController
class AccountController(
    private val commonAccountService: AccountUsecase
) {

    @DeleteMapping("/delete")
    fun accountDelete(request: AccountDeleteRequest): Unit =
        commonAccountService.deleteAccount(request)

    @PutMapping("/dormant")
    fun accountDormant(request: AccountDormantRequest): Unit =
        commonAccountService.dormantAccount(request)
}