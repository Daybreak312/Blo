package com.example.blo.domain.blog.entity

import com.example.blo.domain.account.entity.Account
import com.example.blo.domain.tag.entity.Tag
import com.example.blo.env.TableNameEnv
import javax.persistence.*

@Entity(name = TableNameEnv.blogTable)
class Blog(
    name: String,
    introduction: String?,

    @ManyToOne
    @JoinColumn(name = "account_id")
    val opener: Account
) {
    @Column(unique = true, length = 30)
    var name: String = name
        protected set

    @Column(length = 1000)
    var introduction: String = introduction ?: ""
        protected set

    var totalVisitCount: Long = 0
        protected set

    var todayVisitCount: Long = 0
        protected set

    @OneToMany(mappedBy = "blog_id")
    val tagList: List<Tag> = arrayListOf()

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun updateName(name: String) {
        this.name = name
    }

    fun updateIntroduction(introduction: String) {
        this.introduction = introduction
    }

    fun addTodayVisitCount() {
        this.todayVisitCount++
        this.totalVisitCount++
    }
}