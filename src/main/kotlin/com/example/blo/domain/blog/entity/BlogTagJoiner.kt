package com.example.blo.domain.blog.entity

import com.example.blo.domain.tag.entity.Tag
import com.example.blo.env.TableNameEnv
import javax.persistence.*

@Entity(name = TableNameEnv.blogTagJoinerTable)
data class BlogTagJoiner(
    @ManyToOne
    @JoinColumn(name = "blog_id")
    val blog: Blog,

    @ManyToOne
    @JoinColumn(name = "tag_id")
    val tag: Tag,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)