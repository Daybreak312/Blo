package com.example.blo.domain.auth.function

import com.example.blo.domain.auth.presentation.dto.request.LoginRequest
import com.example.blo.domain.auth.presentation.dto.request.SignRequest
import com.example.blo.env.AccountTestEnv

class AuthTestFunctionImpl : AuthTestFunction {

    override fun createSignRequest() =
        SignRequest(
            name = AccountTestEnv.NAME,
            accountId = AccountTestEnv.ACCOUNT_ID,
            password = AccountTestEnv.PASSWORD,
            reenteredPassword = AccountTestEnv.PASSWORD
        )

    override fun createLoginRequest(): LoginRequest =
        LoginRequest(
            accountId = AccountTestEnv.ACCOUNT_ID,
            password = AccountTestEnv.PASSWORD
        )
}