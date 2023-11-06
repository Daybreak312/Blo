package com.example.blo.domain.blog.service.function

import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.service.exception.BlogNoPermissionException
import com.example.blo.domain.blog.service.exception.InUseBlogNameException
import org.springframework.stereotype.Component

@Component
class BlogFunctionImpl(
    private val blogRepository: BlogRepository,
    private val currentAccountProvider: CurrentAccountProvideUsecase
) : BlogFunction {

    override fun verifyMasterOfBlog(blog: Blog) {
        val currentAccount = currentAccountProvider.getCurrentAccount()
        if (blog.opener.accountId != currentAccount.accountId)
            throw BlogNoPermissionException
    }

    override fun verifyNotUsedBlogName(name: String) {
        if (blogRepository.existsByName(name))
            throw InUseBlogNameException
    }
}