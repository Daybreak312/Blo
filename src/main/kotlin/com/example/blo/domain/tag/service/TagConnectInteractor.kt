package com.example.blo.domain.tag.service

import com.example.blo.domain.account.port.`in`.CurrentAccountProvideUsecase
import com.example.blo.domain.blog.entity.Blog
import com.example.blo.domain.blog.entity.BlogTagJoiner
import com.example.blo.domain.blog.persistence.BlogTagJoinerRepository
import com.example.blo.domain.tag.entity.Tag
import com.example.blo.domain.tag.port.`in`.TagConnectUsecase
import com.example.blo.domain.tag.persistence.TagRepository
import org.springframework.stereotype.Service

@Service
class TagConnectInteractor(
    private val tagRepository: TagRepository,
    private val blogTagJoinerRepository: BlogTagJoinerRepository,
    private val currentAccountProvider: CurrentAccountProvideUsecase
) : TagConnectUsecase {

    override fun connectTagsToBlog(tagNames: List<String>, blog: Blog) {
        val tags = switchTagNamesToTags(tagNames)
        val blogTagJoiners = createBlogTagJoiners(blog, tags)
        blogTagJoinerRepository.saveAll(blogTagJoiners)
    }

    private fun switchTagNamesToTags(tagNames: List<String>): List<Tag> =
        tagNames.map {
            val tag = switchTagNameToTag(it)
            tag.addUsedCount()
            return@map tag
        }

    private fun switchTagNameToTag(tagName: String): Tag =
        tagRepository.findByName(tagName) ?: createTag(tagName)

    private fun createTag(tagName: String): Tag =
        tagRepository.save(Tag(tagName, currentAccountProvider.getCurrentAccount()))

    private fun createBlogTagJoiners(blog: Blog, tags: List<Tag>): List<BlogTagJoiner> =
        tags.map { BlogTagJoiner(blog = blog, tag = it) }
}