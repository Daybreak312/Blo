package com.example.blo.domain.blog

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.blog.function.BlogTestFunction
import com.example.blo.domain.blog.port.`in`.BlogCreateUsecase
import com.example.blo.domain.blog.service.exception.BlogNotFoundException
import com.example.blo.domain.tag.port.`in`.TagExtractUsecase
import com.example.blo.env.BlogTestEnv
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class BlogTests @Autowired constructor(
    private val accountTestFunction: AccountTestFunction,
    private val blogTestFunction: BlogTestFunction,
    private val blogCreateService: BlogCreateUsecase,
    private val tagExtractor: TagExtractUsecase
) {

    @Transactional
    @BeforeEach
    @AfterEach
    fun initialize() {
        accountTestFunction.initialize()
        blogTestFunction.initialize()
    }

    @Transactional
    @Test
    fun blogCreateTest() {
        val testerAccount = accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException
        Assertions.assertTrue(
            blog.name == BlogTestEnv.NAME &&
                    blog.introduction == BlogTestEnv.INTRODUCTION &&
                    blog.opener == testerAccount &&
                    tagExtractor.extractTagsInBlog(blog).map { it.name }.containsAll(BlogTestEnv.TAGS)
        )
    }

    @Transactional
    @Test
    fun blogCreateWithNullIntroductionTest() {
        val testerAccount = accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequestWithNullIntroduction())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException
        Assertions.assertTrue(
            blog.name == BlogTestEnv.NAME &&
                    blog.introduction.isBlank() &&
                    blog.opener == testerAccount &&
                    tagExtractor.extractTagsInBlog(blog).map { it.name }.containsAll(BlogTestEnv.TAGS)
        )
    }
}