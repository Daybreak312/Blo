package com.example.blo.domain.account.presentation.dto.response

import com.example.blo.domain.account.entity.Account

data class AccountSimpleResponse private constructor(
    val name: String,
    val introduction: String
) {
    companion object {
        fun of(account: Account): AccountSimpleResponse =
            AccountSimpleResponse(
                name = account.name,
                introduction = account.introduction
            )
    }
}