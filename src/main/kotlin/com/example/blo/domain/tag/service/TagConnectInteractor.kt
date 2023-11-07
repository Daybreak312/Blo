package com.example.blo.domain.tag.service

import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.entity.BlogTagJoiner
import com.example.blo.domain.blog.persistence.BlogTagJoinerRepository
import com.example.blo.domain.tag.entity.Tag
import com.example.blo.domain.tag.persistence.TagRepository
import com.example.blo.domain.tag.port.`in`.TagConnectUsecase
import com.example.blo.domain.tag.port.`in`.TagExtractUsecase
import com.example.blo.domain.tag.port.`in`.TagNameConvertUsecase
import org.springframework.stereotype.Service

@Service
class TagConnectInteractor(
    private val tagExtractor: TagExtractUsecase,
    private val tagNameConverter: TagNameConvertUsecase,
    private val tagRepository: TagRepository,
    private val blogTagJoinerRepository: BlogTagJoinerRepository,
    private val currentAccountProvider: CurrentAccountProvideUsecase
) : TagConnectUsecase {

    override fun connectTagsToBlog(tagNames: List<String>, blog: Blog) {
        removeUsedTagsInBlog(tagNames, blog)
        val tags = tagNameConverter.convertTagNamesToTags(tagNames.toSet().toList())
        val blogTagJoiners = createBlogTagJoiners(blog, tags)
        blogTagJoinerRepository.saveAll(blogTagJoiners)
    }

    private fun removeUsedTagsInBlog(tagNames: List<String>, blog: Blog): List<String> {
        val blogTagNames: List<String> = tagExtractor.extractTagsInBlog(blog).map { it.name }
        return tagNames.minus(blogTagNames.toSet())
    }

    private fun createTag(tagName: String): Tag =
        tagRepository.save(Tag(tagName, currentAccountProvider.getCurrentAccount()))

    private fun createBlogTagJoiners(blog: Blog, tags: List<Tag>): List<BlogTagJoiner> =
        tags.map { BlogTagJoiner(blog = blog, tag = it) }
}