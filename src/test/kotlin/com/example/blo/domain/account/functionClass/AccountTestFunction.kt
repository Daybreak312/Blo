package com.example.blo.domain.account.functionClass

import com.example.blo.domain.account.entity.Account

interface AccountTestFunction {
    fun initialize()
    fun createAndSaveAndReturnAccount(): Account
}