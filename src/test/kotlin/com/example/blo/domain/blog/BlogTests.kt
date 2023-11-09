package com.example.blo.domain.blog

import com.example.blo.domain.account.functionClass.AccountTestFunction
import com.example.blo.domain.blog.function.BlogTestFunction
import com.example.blo.domain.blog.persistence.BlogRepository
import com.example.blo.domain.blog.port.`in`.BlogCreateUsecase
import com.example.blo.domain.blog.port.`in`.BlogDeleteUsecase
import com.example.blo.domain.blog.port.`in`.BlogUpdateUsecase
import com.example.blo.domain.blog.service.exception.BlogNoPermissionException
import com.example.blo.domain.blog.service.exception.BlogNotFoundException
import com.example.blo.domain.blog.service.function.BlogFunction
import com.example.blo.domain.tag.function.TagTestFunction
import com.example.blo.domain.tag.port.`in`.TagExtractUsecase
import com.example.blo.env.BlogTestEnv
import com.example.blo.env.TagTestEnv
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class BlogTests @Autowired constructor(
    private val accountTestFunction: AccountTestFunction,
    private val blogTestFunction: BlogTestFunction,
    private val blogFunction: BlogFunction,
    private val tagTestFunction: TagTestFunction,
    private val blogRepository: BlogRepository,
    private val blogCreateService: BlogCreateUsecase,
    private val blogUpdateService: BlogUpdateUsecase,
    private val blogDeleteService: BlogDeleteUsecase,
    private val tagExtractor: TagExtractUsecase
) {

    @AfterEach
    @BeforeEach
    fun initialize() {
        accountTestFunction.initialize()
        blogTestFunction.initialize()
        tagTestFunction.initialize()
    }

    @Test
    fun blogCreateTest() {
        val testerAccount = accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException

        Assertions.assertTrue(
            blog.name == BlogTestEnv.NAME &&
                    blog.introduction == BlogTestEnv.INTRODUCTION &&
                    blog.opener == testerAccount &&
                    tagExtractor.extractTagsInBlog(blog).map { it.name }.containsAll(TagTestEnv.TAGS)
        )
    }

    @Test
    fun blogCreateWithNullIntroductionTest() {
        val testerAccount = accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequestWithNullIntroduction())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException

        Assertions.assertTrue(
            blog.name == BlogTestEnv.NAME &&
                    blog.introduction.isBlank() &&
                    blog.opener == testerAccount &&
                    tagExtractor.extractTagsInBlog(blog).map { it.name }.containsAll(TagTestEnv.TAGS)
        )
    }

    @Test
    fun blogUpdateNameTest() {
        accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException
        blogUpdateService.updateBlogName(blog.name, blogTestFunction.createBlogNameUpdateRequest())

        Assertions.assertEquals(blog.name, BlogTestEnv.NAME_UPDATE)
    }

    @Test
    fun blogUpdateIntroductionTest() {
        accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException
        blogUpdateService.updateBlogIntroduction(blog.name, blogTestFunction.createBlogIntroductionUpdateRequest())

        Assertions.assertEquals(blog.introduction, BlogTestEnv.INTRODUCTION_UPDATE)
    }

    @Test
    fun blogUpdateTagTest() {
        accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException
        blogUpdateService.updateBlogTag(blog.name, blogTestFunction.createBlogTagUpdateRequest())

        Assertions.assertEquals(tagExtractor.extractTagsInBlog(blog).map { it.name }, TagTestEnv.TAGS_UPDATE_RESULT)
    }

    @Test
    fun blogUpdateTagWithDuplicatedTagsTest() {
        accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        val blog = blogTestFunction.findTestBlog() ?: throw BlogNotFoundException
        blogUpdateService.updateBlogTag(blog.name, blogTestFunction.createBlogTagUpdateRequestWithDuplicatedTags())

        Assertions.assertEquals(tagExtractor.extractTagsInBlog(blog).map { it.name }, TagTestEnv.TAGS)
    }

    @Test
    fun blogDeleteTest() {
        accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        blogCreateService.createBlog(blogTestFunction.createBlogCreateRequest())
        blogDeleteService.deleteBlog(BlogTestEnv.NAME)

        Assertions.assertFalse(blogRepository.existsByName(BlogTestEnv.NAME))
    }

    @Test
    fun verifyMasterOfBlogWhenTrueTest() {
        val testerAccount = accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        val blog = blogTestFunction.createAndSaveInDBandReturnBlog(testerAccount)

        Assertions.assertDoesNotThrow(fun() { blogFunction.verifyMasterOfBlog(blog) })
    }

    @Test
    fun verifyMasterOfBlogWhenFalseTest() {
        val testerAccount = accountTestFunction.createAndSaveInDBContextAndReturnAccount()
        val blog = blogTestFunction.createAndSaveInDBandReturnBlog(testerAccount)
        accountTestFunction.createAndSaveInDBContextAndReturnAnotherAccount()

        Assertions.assertThrows(BlogNoPermissionException.javaClass, fun() { blogFunction.verifyMasterOfBlog(blog) })
    }
}