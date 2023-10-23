package com.example.blo.domain.account.port.`in`

import com.example.blo.domain.account.entity.Account

interface CurrentAccountProvideUsecase {
    fun getCurrentAccount(): Account
}