package com.example.blo.domain.blog.service

import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.port.`in`.BlogDeleteUsecase
import com.example.blo.domain.blog.service.exception.BlogNotFoundException
import com.example.blo.domain.blog.service.function.BlogFunction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class BlogDeleteInteractor(
    private val function: BlogFunction,
    private val blogRepository: BlogRepository
) : BlogDeleteUsecase {

    override fun deleteBlog(blogName: String) {
        val blog = blogRepository.findByName(blogName) ?: throw BlogNotFoundException
        function.verifyMasterOfBlog(blog)
        blogRepository.delete(blog)
    }
}