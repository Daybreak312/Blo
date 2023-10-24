package com.example.blo.domain.account.functionClass

import com.example.blo.domain.account.entity.Account

interface AccountTestFunction {
    fun initialize()
    fun createAndReturnAccount(): Account
    fun createAndSaveInDBAndReturnAccount(): Account
    fun createAndSaveInDBContextAndReturnAccount(): Account
}