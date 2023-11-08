package com.example.blo.domain.account.presentation.dto.response

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.presentation.dto.response.BlogSimpleResponse

data class AccountDetailResponse private constructor(
    val name: String,
    val accountId: String,
    val introduction: String,
    val blogList: List<BlogSimpleResponse>
) {
    companion object {
        fun of(account: Account): AccountDetailResponse =
            AccountDetailResponse(
                name = account.name,
                accountId = account.accountId,
                introduction = account.introduction,
                blogList = account.blogs.map {
                    BlogSimpleResponse.of(it)
                }
            )
    }
}